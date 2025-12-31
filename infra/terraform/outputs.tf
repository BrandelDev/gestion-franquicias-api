output "rds_endpoint" {
  description = "Aurora cluster endpoint address."
  value       = aws_rds_cluster.db.endpoint
}

output "alb_dns_name" {
  description = "ALB DNS name."
  value       = aws_lb.app.dns_name
}

output "ecr_repository_url" {
  description = "ECR repository URL."
  value       = aws_ecr_repository.app.repository_url
}

output "codepipeline_name" {
  description = "CodePipeline name."
  value       = aws_codepipeline.app.name
}

output "codestar_connection_arn" {
  description = "CodeStar connection ARN (needs manual authorization)."
  value       = aws_codestarconnections_connection.github.arn
}

output "ssm_username_parameter" {
  description = "SSM parameter for DB username."
  value       = aws_ssm_parameter.db_username.name
}

output "ssm_password_parameter" {
  description = "SSM parameter for DB password."
  value       = aws_ssm_parameter.db_password.name
}
