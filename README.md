# HuffmanCoder

A full-stack Huffman Coding application that provides **lossless file compression and decompression** through a RESTful API. The backend is built with **Spring Boot** and integrates a custom Huffman Coding engine developed from scratch.

> ⚠️ Frontend is currently under development.

---

## ✨ Features

- 📁 Compress text files into a custom `.huff` format
- 📂 Decompress `.huff` files back to their original content
- 🚀 REST APIs built with Spring Boot
- 📤 File upload using `MultipartFile`
- 📥 Automatic file download with original filename preservation
- 🧹 Temporary file management
- ⚠️ Global exception handling
- ✅ File validation
- 🌐 CORS configuration for frontend integration
- 📝 Logging using SLF4J

---

## 🏗️ Architecture

```
                Client
                   │
                   ▼
         HuffmanController
                   │
                   ▼
          HuffmanService
         ┌─────────┴─────────┐
         ▼                   ▼
 HuffmanCompressor   HuffmanDecompressor
         │                   │
         ▼                   ▼
     Huffman Engine (Custom Implementation)
```

---

## 🛠️ Tech Stack

### Backend

- Java 21
- Spring Boot 4.1
- Maven
- SLF4J Logging

### Core Algorithms

- Huffman Coding
- Binary Tree
- Custom Bit Input/Output Streams
- Custom `.huff` File Format

---

## 📂 Project Structure

```
src/main/java/com/prakhar/huffman
│
├── compression
├── controller
├── exception
├── io
├── model
├── service
├── util
├── config
└── HuffmanApplication.java
```

---

## 📡 API Endpoints

### Compress File

```
POST /compress
```

**Request**

- multipart/form-data

| Parameter | Type | Description |
|----------|------|-------------|
| file | File | Input text file |

**Response**

- Compressed `.huff` file

---

### Decompress File

```
POST /decompress
```

**Request**

- multipart/form-data

| Parameter | Type | Description |
|----------|------|-------------|
| file | File | Input `.huff` file |

**Response**

- Original decompressed file

---

## 🚀 Running the Project

### Clone the repository

```bash
git clone https://github.com/Prakh4r/HuffmanCoderWeb.git
```

### Navigate to the project

```bash
cd HuffmanCoderWeb
```

### Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

The application starts at:

```
http://localhost:8080
```

---

## 📌 Current Status

### Backend

- ✅ Compression API
- ✅ Decompression API
- ✅ Validation
- ✅ Exception Handling
- ✅ Logging
- ✅ CORS

### Frontend

- 🚧 React frontend under development

---

## 🔮 Future Improvements

- React Frontend
- Drag & Drop File Upload
- Progress Indicator
- Compression Statistics
- Deployment
- Docker Support
- API Documentation (Swagger/OpenAPI)

---

## 👨‍💻 Author

**Prakhar Dhawan**

GitHub: https://github.com/Prakh4r
