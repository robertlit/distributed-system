Goals:
1. Build a containerized application using the microservices architecture
2. Deploy the application on the cloud with Kubernetes
3. Configure the required infrastructure with Terraform's IaC approach

Non-goals:
1. Create a useful service or API

# Structure
| Name         | Framework  | Information                                                                     |
| ------------ | ---------- |---------------------------------------------------------------------------------|
| api-gateway  | Spring WebFlux, Spring Cloud Gateway | Centralizes communication between services and exposes them | 
| user-service | Spring Web | Responsible for information about some "users"                                  |
| content-service | Spring Web | Responsible for information about some "content"                                |
| recommendation-service | Spring WebFlux | Aggregates user and content data and produces content recommendations for users |

*Note*: Each service is intentionally a separate Gradle project, rather than a module
of a parent project. That is because microservices should ideally be decoupled.
However, for convenience, they are all put in this single repository.

# Deploying

## Create a Google Cloud project
Create a new project in the GCP Console. Enable the Compute Engine API,
Kubernetes Engine API, Cloud Build API and Artifact Registry API.

## Setup the Google Cloud client
```
gcloud init
gcloud auth application-default login
gcloud components install kubectl
gcloud components install gke-gcloud-auth-plugin
```

## Prepare the infrastructure with Terraform
Configure the variables in `terraform.tfvars`, then run
```
terraform init
terraform apply
```

## Build Docker images
For each service run
```
gcloud builds submit --tag <region>-docker.pkg.dev/<project>/<repo>/<service_name> .
```

## Create Kubernetes deployments and services
For each service, configure the image location in the ```deployment.yaml```
file, then run
```
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

# Sources
- [Deploy an app in a container image to a GKE cluster](https://cloud.google.com/kubernetes-engine/docs/quickstarts/deploy-app-container-image)
- [Provision a GKE Cluster](https://developer.hashicorp.com/terraform/tutorials/kubernetes/gke)
- [Configure private services access](https://cloud.google.com/vpc/docs/configure-private-services-access#)
- [Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)
- [Service discovery and DNS](https://cloud.google.com/kubernetes-engine/docs/concepts/service-discovery)
- [Inter-cluster communication between microservices on Kubernetes](https://stackoverflow.com/questions/61110127/best-way-for-inter-cluster-communication-between-microservices-on-kubernetes)
- [kubectl authentication in GKE](https://cloud.google.com/blog/products/containers-kubernetes/kubectl-auth-changes-in-gke)

