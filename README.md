# HuffmanCoder

A modern full-stack web application that performs **lossless file compression and decompression** using the **Huffman Coding Algorithm**. The project features a custom Huffman engine implemented from scratch, exposed through a Spring Boot REST API and a responsive React frontend.

---

## 🌐 Live Demo

**Frontend:** https://huffman-coder-web.vercel.app

**Backend:** https://huffmancoderweb-production.up.railway.app

---

## 📸 Preview

> Add screenshots here after uploading them.

| Home Page | File Selected |
|-----------|---------------|
| ![Home](assets/home.png) | ![Upload](assets/upload.png) |

---

## ✨ Features

- 📂 Compress files into a custom `.huff` format
- 📄 Decompress `.huff` files back to their original form
- 🌳 Custom Huffman Tree implementation
- ⚡ Bit-level compression using custom input/output streams
- 📦 Custom `.huff` file format with metadata header
- 🌐 RESTful API built using Spring Boot
- 📤 Drag & Drop file upload
- 📥 Automatic file download with original filename preservation
- ⚠️ Global exception handling
- ✅ File validation
- 📝 Logging using SLF4J
- 🔒 CORS configuration
- 🎨 Modern responsive React UI

---

# 🏗️ Architecture

```
                    React + TypeScript
                           │
                           ▼
                      Axios Client
                           │
                           ▼
                Spring Boot REST API
                           │
                    HuffmanService
                    ┌────────────┐
                    ▼            ▼
          HuffmanCompressor   HuffmanDecompressor
                    │            │
                    ▼            ▼
            Huffman Coding Engine
                    │
                    ▼
           Compressed File Download
```

---

# 🛠 Tech Stack

## Backend

- Java 21
- Spring Boot 4
- Maven
- REST API
- SLF4J Logging

## Frontend

- React 19
- TypeScript
- Vite
- Tailwind CSS
- Axios
- Lucide React

## Algorithm

- Huffman Coding
- Binary Trees
- Priority Queue
- Bit Manipulation
- File Compression

---

# 📂 Project Structure

```
HuffmanCoderWeb
│
├── src/                      # Spring Boot Backend
│
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
├── pom.xml
└── README.md
```

---

# 🚀 REST API

## Compress File

### Endpoint

```
POST /compress
```

### Request

```
multipart/form-data
```

Parameter

| Name | Type |
|------|------|
| file | File |

### Response

Compressed `.huff` file

---

## Decompress File

### Endpoint

```
POST /decompress
```

### Request

```
multipart/form-data
```

Parameter

| Name | Type |
|------|------|
| file | File |

### Response

Original decompressed file

---

# ⚙️ Running Locally

## Clone

```bash
git clone https://github.com/Prakh4r/HuffmanCoderWeb.git
```

---

## Backend

```bash
mvn spring-boot:run
```

Runs at

```
http://localhost:8080
```

---

## Frontend

```bash
cd frontend

npm install

npm run dev
```

Runs at

```
http://localhost:5174
```

---

# 📖 How It Works

1. User uploads a file.
2. React sends the file to the Spring Boot backend.
3. The backend constructs a Huffman Tree using character frequencies.
4. Huffman codes are generated.
5. Data is encoded using bit-level compression.
6. A custom `.huff` file is created containing:
    - Header
    - Frequency table
    - Encoded bit stream
7. The compressed file is returned to the frontend for download.
8. During decompression, the Huffman Tree is reconstructed using the stored header and the original file is restored.

---

# 📌 Future Improvements

- Compression statistics (Original vs Compressed size)
- Compression ratio visualization
- Multiple file upload
- Folder compression
- Dark mode
- Docker support
- Unit and integration testing

---

# 👨‍💻 Author

**Prakhar Dhawan**

- GitHub: https://github.com/Prakh4r
- LinkedIn: *https://www.linkedin.com/in/prakhar-dhawan-57970835a/*

---

## ⭐ If you found this project useful, consider giving it a star!