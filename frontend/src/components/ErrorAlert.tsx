import React from 'react';
import { AlertCircle, X } from 'lucide-react';

interface ErrorAlertProps {
  message: string;
  onClose: () => void;
}

const ErrorAlert: React.FC<ErrorAlertProps> = ({ message, onClose }) => {
  return (
    <div className="w-full max-w-2xl mx-auto mb-6">
      <div className="bg-red-50 border border-red-200 p-4 rounded-xl shadow-sm flex items-start space-x-3 animate-in fade-in slide-in-from-top-4 duration-300">
        <AlertCircle className="text-red-500 mt-0.5 flex-shrink-0" size={20} />
        <div className="flex-1">
          <h4 className="text-red-800 font-medium">Error Occurred</h4>
          <p className="text-red-600 text-sm mt-1">{message}</p>
        </div>
        <button
          onClick={onClose}
          className="text-red-400 hover:text-red-600 hover:bg-red-100 p-1.5 rounded-full transition-colors"
        >
          <X size={18} />
        </button>
      </div>
    </div>
  );
};

export default ErrorAlert;
