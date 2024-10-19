output "db_endpoint" {
  description = "The endpoint of the RDS instance"
  value       = aws_db_instance.franchises-crud-bd.endpoint
}