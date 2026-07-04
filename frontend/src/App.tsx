import React, { useState } from 'react';
import Hero from './components/Hero';
import UploadCard from './components/UploadCard';
import ActionButtons from './components/ActionButtons';
import LoadingOverlay from './components/LoadingOverlay';
import ErrorAlert from './components/ErrorAlert';
import SuccessAlert from './components/SuccessAlert';
import { compressFile, decompressFile } from './services/api';
import type { FileResponse } from './services/api';

const App: React.FC = () => {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [loadingMessage, setLoadingMessage] = useState<string>('');
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  const handleDownload = (response: FileResponse) => {
    const url = window.URL.createObjectURL(response.blob);
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', response.filename);
    document.body.appendChild(link);
    link.click();
    
    // Clean up
    link.parentNode?.removeChild(link);
    window.URL.revokeObjectURL(url);
  };

  const handleApiError = (err: any, fallbackMessage: string) => {
    let errorMessage = fallbackMessage;
    if (err.response && err.response.data instanceof Blob) {
      // Sometimes backend sends error as a Blob when responseType is 'blob'
      const reader = new FileReader();
      reader.onload = () => {
        try {
          const errorData = JSON.parse(reader.result as string);
          setError(errorData.message || fallbackMessage);
        } catch (e) {
          setError(fallbackMessage);
        }
      };
      reader.readAsText(err.response.data);
      return;
    } else if (err.response && err.response.data && err.response.data.message) {
      errorMessage = err.response.data.message;
    } else if (err.message) {
      errorMessage = err.message;
    }
    setError(errorMessage);
  };

  const handleCompress = async () => {
    if (!selectedFile) return;
    
    setIsLoading(true);
    setLoadingMessage('Compressing...');
    setError(null);
    setSuccess(null);

    try {
      const response = await compressFile(selectedFile);
      handleDownload(response);
      setSuccess('File compressed and downloaded successfully.');
    } catch (err: any) {
      handleApiError(err, 'Failed to compress the file.');
    } finally {
      setIsLoading(false);
    }
  };

  const handleDecompress = async () => {
    if (!selectedFile) return;
    
    setIsLoading(true);
    setLoadingMessage('Decompressing...');
    setError(null);
    setSuccess(null);

    try {
      const response = await decompressFile(selectedFile);
      handleDownload(response);
      setSuccess('File decompressed and downloaded successfully.');
    } catch (err: any) {
      handleApiError(err, 'Failed to decompress the file.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen relative overflow-hidden flex flex-col">
      {/* Background decorations for a premium look */}
      <div className="absolute top-[-10%] left-[-10%] w-96 h-96 bg-brand-400 rounded-full mix-blend-multiply filter blur-3xl opacity-20 animate-blob" />
      <div className="absolute top-[-10%] right-[-10%] w-96 h-96 bg-purple-400 rounded-full mix-blend-multiply filter blur-3xl opacity-20 animate-blob animation-delay-2000" />
      <div className="absolute bottom-[-20%] left-[20%] w-96 h-96 bg-pink-400 rounded-full mix-blend-multiply filter blur-3xl opacity-20 animate-blob animation-delay-4000" />
      
      <main className="flex-1 relative z-10 container mx-auto px-4 py-8 md:py-12 flex flex-col">
        <Hero />

        <div className="flex-1 flex flex-col items-center justify-start w-full max-w-4xl mx-auto pt-6">
          {error && <ErrorAlert message={error} onClose={() => setError(null)} />}
          {success && <SuccessAlert message={success} onClose={() => setSuccess(null)} />}

          <UploadCard 
            selectedFile={selectedFile} 
            onFileSelect={setSelectedFile} 
            disabled={isLoading}
          />
          
          <ActionButtons 
            onCompress={handleCompress}
            onDecompress={handleDecompress}
            disabled={!selectedFile || isLoading}
          />
        </div>
      </main>

      {isLoading && <LoadingOverlay message={loadingMessage} />}
    </div>
  );
};

export default App;
