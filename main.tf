terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"
    }
  }
}

provider "aws" {
  region     = "us-east-1"
  access_key = var.access_key
  secret_key = var.secret_key
}

resource "aws_security_group" "franchises-crud-vpc" {
  name        = "franchises-crud-vpc"
  description = "franchises-crud-vpc"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["18.206.107.24/29"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "franchises-bd-vpc" {
  name        = "franchises-bd-vpc"
  description = "franchises-bd-vpc"

  ingress {
    from_port = 3306
    to_port   = 3306
    protocol  = "tcp"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group_rule" "allow_ec2_to_db" {
  type                     = "ingress"
  from_port                = 3306
  to_port                  = 3306
  protocol                 = "tcp"
  security_group_id        = aws_security_group.franchises-bd-vpc.id
  source_security_group_id = aws_security_group.franchises-crud-vpc.id
}


resource "aws_db_instance" "franchises-crud-bd" {
  allocated_storage      = 20
  engine                 = "mysql"
  engine_version         = "8.0"
  instance_class         = "db.t3.micro"
  username               = var.db_user
  password               = var.db_password
  parameter_group_name   = "default.mysql8.0"
  skip_final_snapshot    = true
  vpc_security_group_ids = [aws_security_group.franchises-bd-vpc.id]
}

resource "aws_instance" "franchises-crud" {
  ami                    = "ami-011899242bb902164"
  instance_type          = "t2.micro"
  user_data              = <<-EOF
              #!/bin/bash
              sudo apt update -y
              sudo apt install -y git mysql-client-core-8.0 docker.io docker-compose vim
              mkdir -p /home/ubuntu/franchises-crud
              git clone https://github.com/sochoa885/franquicias-crud.git /home/ubuntu/franchises-crud
              sudo chown -R ubuntu:ubuntu /home/ubuntu/franchises-crud
              mysql --host=${aws_db_instance.franchises-crud-bd.address} --user=${var.db_user} --password=${var.db_password} -e "CREATE DATABASE public;"
              EOF
  vpc_security_group_ids = [aws_security_group.franchises-crud-vpc.id]
  depends_on             = [aws_db_instance.franchises-crud-bd]
}