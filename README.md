# Tomcat Servlet Project

This repository demonstrates the basics of Java Servlet programming using Apache Tomcat. It includes hands-on modules for understanding HTTP request handling, servlet lifecycle, and simple form-based login mechanisms.

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Concepts Demonstrated](#concepts-demonstrated)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)

## Overview

The `Tomcat_Servlet` project introduces core web components using Java Servlets. The project is split into modules that help understand different servlet operations including HTTP GET/POST handling, form submissions, and basic user authentication.

Modules included:
- **DemoServlet** – A basic servlet to demonstrate HTTP GET handling.
- **Login** – Implements form-based login using HTTP POST requests.

## Technologies Used

- Java SE
- Java Servlet API
- Apache Tomcat (v9 or later)
- HTML (for front-end forms)
- No external dependencies

## Concepts Demonstrated

- Servlet lifecycle methods: `init()`, `service()`, `doGet()`, `doPost()`, and `destroy()`
- HTTP request and response handling
- Form data capture and processing
- Deployment using `web.xml`
- Servlet mapping and routing

## Project Structure

- **DemoServlet/**
  - Basic servlet class for GET request demonstration
  - `web.xml` configuration for servlet mapping

- **Login/**
  - HTML form for user login
  - `LoginServlet.java` to handle form POST submission
  - Servlet configuration using `web.xml`

- **README.md**
  - Documentation and guide for using this project

## How to Run

1. Install and configure [Apache Tomcat](https://tomcat.apache.org/).
2. Clone this repository:
   ```bash
   git clone https://github.com/KillerVardhan8/Tomcat_Servlet.git
