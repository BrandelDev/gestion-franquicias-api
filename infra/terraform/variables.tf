variable "region" {
  type        = string
  description = "AWS region for deployment."
  default     = "us-east-2"
}

variable "app_name" {
  type        = string
  description = "Application name."
  default     = "gestion-franquicias"
}

variable "env_name" {
  type        = string
  description = "Environment name."
  default     = "prod"
}

variable "db_instance_class" {
  type        = string
  description = "Aurora DB instance class."
  default     = "db.t4g.medium"
}

variable "db_engine_version" {
  type        = string
  description = "Aurora MySQL engine version."
  default     = "8.0.mysql_aurora.3.04.0"
}

variable "db_name" {
  type        = string
  description = "Database name."
  default     = "db_franquicias"
}

variable "db_username" {
  type        = string
  description = "Database username."
  default     = "franquicias_admin"
}

variable "ssm_path_prefix" {
  type        = string
  description = "SSM parameter path prefix."
  default     = "/gestion-franquicias/prod"
}

variable "ecs_cpu" {
  type        = number
  description = "ECS task CPU units."
  default     = 256
}

variable "ecs_memory" {
  type        = number
  description = "ECS task memory (MiB)."
  default     = 512
}

variable "container_port" {
  type        = number
  description = "Container port exposed by the app."
  default     = 8080
}

variable "github_owner" {
  type        = string
  description = "GitHub repository owner."
  default     = "BrandelDev"
}

variable "github_repo" {
  type        = string
  description = "GitHub repository name."
  default     = "gestion-franquicias-api"
}

variable "github_branch" {
  type        = string
  description = "GitHub branch name."
  default     = "main"
}

variable "pipeline_name" {
  type        = string
  description = "CodePipeline name."
  default     = "gestion-franquicias-pipeline"
}

variable "build_project_name" {
  type        = string
  description = "CodeBuild project name."
  default     = "gestion-franquicias-build"
}

variable "tags" {
  type        = map(string)
  description = "Tags applied to resources."
  default = {
    project = "gestion-franquicias"
    env     = "prod"
  }
}
