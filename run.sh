#!/bin/bash

echo "=== Dang chay kiem tra Checkstyle ==="
mvn clean checkstyle:check

echo "=== Dang bien dich du an ==="
mvn compile

echo "=== Hoan thanh ==="