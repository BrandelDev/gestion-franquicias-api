variable "region" {
  type        = string
  description = "AWS region for deployment."
  default     = "us-east-2"
}

variable "app_name" {
  type        = string
  description = "Elastic Beanstalk application name."
  default     = "gestion-franquicias"
}

variable "env_name" {
  type        = string
  description = "Elastic Beanstalk environment name."
  default     = "prod"
}

variable "solution_stack_name" {
  type        = string
  description = "Elastic Beanstalk solution stack."
  default     = "64bit Amazon Linux 2 v3.5.8 running Docker"
}

variable "instance_type" {
  type        = string
  description = "EC2 instance type for Elastic Beanstalk."
  default     = "t3.micro"
}

variable "db_instance_class" {
  type        = string
  description = "RDS instance class."
  default     = "db.t3.micro"
}

variable "db_engine_version" {
  type        = string
  description = "MySQL engine version."
  default     = "8.0.36"
}

variable "db_allocated_storage" {
  type        = number
  description = "RDS storage in GB."
  default     = 20
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

variable "tags" {
  type        = map(string)
  description = "Tags applied to resources."
  default = {
    project = "gestion-franquicias"
    env     = "prod"
  }
}


