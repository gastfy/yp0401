package com.example.demo.errors

class BookNotFound(message: String) : RuntimeException(message) {}