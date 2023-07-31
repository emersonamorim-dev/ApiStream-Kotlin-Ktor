provider "aws" {
  region = "us-west-2"
}

resource "aws_instance" "Api-Stream" {
  ami           = "ami-0c94855ba95c574c8"
  instance_type = "t2.micro"

  tags = {
    Name = "Api-StreamInstance"
  }
}
