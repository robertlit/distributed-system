variable "sql_user_service_password" {
  default     = ""
  description = "password for the sql user of the user service"
}

variable "sql_content_service_password" {
  default     = ""
  description = "password for the sql user of the content service"
}

resource "google_sql_database" "users" {
  name     = "users"
  instance = google_sql_database_instance.main.name
}

resource "google_sql_database" "content" {
  name     = "content"
  instance = google_sql_database_instance.main.name
}

resource "google_sql_database_instance" "main" {
  name             = "main-instance"
  region           = var.region
  database_version = "MYSQL_8_0"

  depends_on = [google_service_networking_connection.default]

  settings {
    tier = "db-f1-micro"

    ip_configuration {

      # Didn't manage configure VPC correctly unfortunately
      authorized_networks {
        name  = "test-all"
        value = "0.0.0.0/0"
      }

      #      ipv4_enabled                                  = false
      private_network                               = google_compute_network.vpc.id
      enable_private_path_for_google_cloud_services = true
    }
  }

  deletion_protection = "true"
}

resource "google_sql_user" "user_service" {
  name     = "user_service"
  instance = google_sql_database_instance.main.name
  password = var.sql_user_service_password
}

resource "google_sql_user" "content_service" {
  name     = "content_service"
  instance = google_sql_database_instance.main.name
  password = var.sql_content_service_password
}

resource "kubernetes_secret" "database_credentials" {
  metadata {
    name = "database-credentials"
  }

  data = {
    "db-ip"                       = google_sql_database_instance.main.ip_address.0.ip_address
    "user-service-db-password"    = var.sql_user_service_password
    "content-service-db-password" = var.sql_user_service_password
  }
}

resource "kubernetes_config_map" "database_information" {
  metadata {
    name = "database-information"
  }

  data = {
    "user-service-db-name"        = google_sql_database.users.name
    "user-service-db-username"    = google_sql_user.user_service.name
    "content-service-db-name"     = google_sql_database.content.name
    "content-service-db-username" = google_sql_user.content_service.name
  }
}