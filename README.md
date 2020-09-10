# ProductAPI
API using Akka HTTP and Slick integrated with PostgresSQL

## Deployment On Kubernetes

To clone and deploy this application, you'll need Git, Minikube and Kubectl installed on your computer.

#### Launch Minikube cluster

```bash
# Start by cloning the repository
$ git clone https://github.com/shubham-kanodia/AkkaHTTP.git

# Change working directory to K8
$ cd K8

# Start Minikube cluster
$ minikube start --driver=drive_name
```

#### Create Resources for Postgres Server

```bash
# Create PersistentVolumeClaim
# To know why we do not need to create a storage class refer: https://platform9.com/blog/tutorial-dynamic-provisioning-of-persistent-storage-in-kubernetes-with-minikube/
$ kubectl apply -f persistent-volume-claim.yaml

# To check if the claim has been created
$ kubectl get pvc

# Create Config Map Resource
$ kubectl apply -f config-map-postgres.yaml

# Create Stateful Set Resource
$ kubectl apply -f stateful-set.yaml

# Create NodePort Service
$ kubectl apply -f nodeport-service-postgres.yaml

# View Service URL
$ minikube service postgres-service --url
```
Note: To check if postgresql is correctly configured use command `psql -h host_name -p port_name -U user_name -d db_name`, here host_name and port_name are host and port of postgres-service respectively. At this point you must be able to access the postgres database.

#### Create Resources for REST API Server

```bash
# Create Deployment Resource
$ kubectl apply -f api-deployment.yaml

# Deploy NodePort Service
$ kubectl apply -f api-nodeport.yaml

# View exposed URL for accessing API endpoints
$ minikube service product-service --url
```
