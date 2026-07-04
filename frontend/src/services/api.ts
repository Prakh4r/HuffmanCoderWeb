import axios from 'axios';

const API_BASE_URL = "https://huffmancoderweb-production.up.railway.app";

const apiClient = axios.create({
  baseURL: API_BASE_URL,
});

export interface FileResponse {
  blob: Blob;
  filename: string;
}

const extractFilename = (contentDisposition: string | undefined, defaultName: string): string => {
  if (contentDisposition && contentDisposition.includes('filename=')) {
    const filenameMatch = contentDisposition.match(/filename="?([^"]+)"?/);
    if (filenameMatch && filenameMatch.length > 1) {
      return filenameMatch[1];
    }
  }
  return defaultName;
};

export const compressFile = async (file: File): Promise<FileResponse> => {
  const formData = new FormData();
  formData.append('file', file);

  const response = await apiClient.post('/compress', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    responseType: 'blob',
  });
  
  const filename = extractFilename(response.headers['content-disposition'], 'compressed.huff');
  return { blob: response.data, filename };
};

export const decompressFile = async (file: File): Promise<FileResponse> => {
  const formData = new FormData();
  formData.append('file', file);

  const response = await apiClient.post('/decompress', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    responseType: 'blob',
  });
  
  const filename = extractFilename(response.headers['content-disposition'], 'decompressed_file');
  return { blob: response.data, filename };
};
