resource "google_artifact_registry_repository" "repo" {
  location      = var.region
  repository_id = "${var.project_id}-repo"
  description   = "repository for images deployed to gke"
  format        = "DOCKER"
}