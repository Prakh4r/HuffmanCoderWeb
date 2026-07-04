import React, { useCallback, useRef, useState } from 'react';
import { UploadCloud, File as FileIcon, X } from 'lucide-react';

interface UploadCardProps {
  selectedFile: File | null;
  onFileSelect: (file: File | null) => void;
  disabled?: boolean;
}

const UploadCard: React.FC<UploadCardProps> = ({ selectedFile, onFileSelect, disabled = false }) => {
  const [isDragging, setIsDragging] = useState(false);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleDragOver = useCallback((e: React.DragEvent) => {
    e.preventDefault();
    if (!disabled) setIsDragging(true);
  }, [disabled]);

  const handleDragLeave = useCallback((e: React.DragEvent) => {
    e.preventDefault();
    setIsDragging(false);
  }, []);

  const handleDrop = useCallback((e: React.DragEvent) => {
    e.preventDefault();
    setIsDragging(false);
    if (disabled) return;
    
    if (e.dataTransfer.files && e.dataTransfer.files.length > 0) {
      onFileSelect(e.dataTransfer.files[0]);
      e.dataTransfer.clearData();
    }
  }, [disabled, onFileSelect]);

  const handleFileInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      onFileSelect(e.target.files[0]);
    }
  };

  const handleBrowseClick = () => {
    if (!disabled) fileInputRef.current?.click();
  };

  const formatFileSize = (bytes: number): string => {
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
  };

  return (
    <div className="w-full max-w-2xl mx-auto mb-8">
      {!selectedFile ? (
        <div
          className={`
            glass-panel relative flex flex-col items-center justify-center p-12 text-center border-2 border-dashed
            transition-all duration-300 ease-in-out cursor-pointer group
            ${isDragging ? 'border-brand-500 bg-brand-50' : 'border-slate-300 hover:border-brand-400 hover:bg-slate-50/50'}
            ${disabled ? 'opacity-50 cursor-not-allowed' : ''}
          `}
          onDragOver={handleDragOver}
          onDragLeave={handleDragLeave}
          onDrop={handleDrop}
          onClick={handleBrowseClick}
        >
          <input
            type="file"
            className="hidden"
            ref={fileInputRef}
            onChange={handleFileInput}
            disabled={disabled}
          />
          <div className={`p-4 rounded-full mb-4 transition-colors duration-300 ${isDragging ? 'bg-brand-100 text-brand-600' : 'bg-slate-100 text-slate-400 group-hover:bg-brand-50 group-hover:text-brand-500'}`}>
            <UploadCloud size={40} strokeWidth={1.5} />
          </div>
          <h3 className="text-lg font-semibold text-slate-700 mb-2">
            Drag & Drop your file here
          </h3>
          <p className="text-slate-500 text-sm mb-6">
            Supports any file for compression, and .huff for decompression
          </p>
          <button 
            className="px-6 py-2.5 bg-white border border-slate-200 text-slate-700 rounded-lg shadow-sm hover:bg-slate-50 hover:text-brand-600 transition-colors font-medium z-10 focus:outline-none focus:ring-2 focus:ring-brand-500 focus:ring-offset-1"
            onClick={(e) => {
              e.stopPropagation();
              handleBrowseClick();
            }}
            disabled={disabled}
          >
            Browse File
          </button>
        </div>
      ) : (
        <div className="glass-panel p-6 flex items-center justify-between transition-all duration-300">
          <div className="flex items-center space-x-4 overflow-hidden">
            <div className="p-3 bg-brand-50 text-brand-600 rounded-xl flex-shrink-0">
              <FileIcon size={32} strokeWidth={1.5} />
            </div>
            <div className="flex flex-col overflow-hidden">
              <span className="font-semibold text-slate-800 truncate" title={selectedFile.name}>
                {selectedFile.name}
              </span>
              <div className="flex items-center space-x-2 text-sm text-slate-500 mt-1">
                <span>{formatFileSize(selectedFile.size)}</span>
                <span className="w-1 h-1 bg-slate-300 rounded-full"></span>
                <span className="uppercase text-xs font-semibold tracking-wider bg-slate-100 px-2 py-0.5 rounded-md">
                  {selectedFile.name.split('.').pop() || 'Unknown'}
                </span>
              </div>
            </div>
          </div>
          
          <button
            onClick={() => onFileSelect(null)}
            disabled={disabled}
            className="p-2 text-slate-400 hover:text-red-500 hover:bg-red-50 rounded-full transition-colors disabled:opacity-50 flex-shrink-0 ml-4"
            title="Remove file"
          >
            <X size={20} />
          </button>
        </div>
      )}
    </div>
  );
};

export default UploadCard;
