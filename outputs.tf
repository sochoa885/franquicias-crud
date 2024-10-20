output "db_endpoint" {
  description = "The endpoint of the RDS instance"
  value       = aws_db_instance.franchises-crud-bd.endpoint
}

output "instance_public_ip" {
  value = aws_instance.franchises-crud.public_ip
}