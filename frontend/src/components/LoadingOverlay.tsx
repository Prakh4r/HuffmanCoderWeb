import React from 'react';
import { Loader2 } from 'lucide-react';

interface LoadingOverlayProps {
  message: string;
}

const LoadingOverlay: React.FC<LoadingOverlayProps> = ({ message }) => {
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-slate-900/40 backdrop-blur-sm transition-opacity">
      <div className="bg-white p-8 rounded-2xl shadow-2xl flex flex-col items-center max-w-sm w-full mx-4 border border-white/50">
        <Loader2 className="animate-spin text-brand-600 mb-4" size={48} strokeWidth={2} />
        <h3 className="text-xl font-semibold text-slate-800">{message}</h3>
        <p className="text-slate-500 mt-2 text-sm text-center">
          Please wait while we process your file...
        </p>
      </div>
    </div>
  );
};

export default LoadingOverlay;
