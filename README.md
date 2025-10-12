# 🛡️ Data Validator

### Hexlet tests and linter status:
[![Actions Status](https://github.com/AnnaChekina/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AnnaChekina/java-project-78/actions)
[![Java CI](https://github.com/AnnaChekina/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/AnnaChekina/java-project-78/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AnnaChekina_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=AnnaChekina_java-project-78)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=AnnaChekina_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=AnnaChekina_java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=AnnaChekina_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=AnnaChekina_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AnnaChekina_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=AnnaChekina_java-project-78)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=AnnaChekina_java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=AnnaChekina_java-project-78)

A lightweight Java validation library with fluent interface for type-safe data validation.

## Setup

```bash
make build
```

## Run tests

```bash
make test
```

## Run checkstyle

```bash
make checkstyle
```

## 📚 API

### StringSchema
- `.required()` - Non-null and non-empty
- `.minLength(int)` - Minimum string length
- `.contains(String)` - Must contain substring

### NumberSchema
- `.required()` - Non-null
- `.positive()` - Positive numbers only
- `.range(int, int)` - Value within range

### MapSchema
- `.required()` - Non-null map
- `.sizeof(int)` - Exact size validation
- `.shape(Map)` - Nested field validation
