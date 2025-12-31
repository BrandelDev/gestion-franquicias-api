# ECS Fargate + Aurora + CodePipeline (Terraform)

This Terraform stack provisions:
- ECS Fargate service behind an ALB (public HTTP).
- Aurora MySQL cluster in the default VPC.
- ECR repository for container images.
- CodePipeline + CodeBuild to build and deploy from GitHub.
- SSM Parameter Store entries for DB username/password.

## Prerequisites
- AWS credentials configured locally.
- Terraform 1.5+.
- GitHub connection authorization in CodeStar Connections.

## Deploy
```bash
cd infra/terraform
terraform init
terraform apply
```

## After apply
1) Authorize the CodeStar connection in AWS Console.
2) Trigger a pipeline run (or push to `main`).
3) Use the ALB DNS output as the API base URL.

## Notes
- DB secrets are stored in SSM:
  - `/gestion-franquicias/prod/db/username`
  - `/gestion-franquicias/prod/db/password`
- The ECS task reads DB credentials from SSM SecureString.
