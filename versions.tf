terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "4.71.0"
    }

    kubernetes = {
      source = "hashicorp/kubernetes"
    }
  }

  required_version = ">= 0.14"
}