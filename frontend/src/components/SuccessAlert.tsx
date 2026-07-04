import React, { useEffect } from 'react';
import { CheckCircle2, X } from 'lucide-react';

interface SuccessAlertProps {
  message: string;
  onClose: () => void;
}

const SuccessAlert: React.FC<SuccessAlertProps> = ({ message, onClose }) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      onClose();
    }, 5000);
    return () => clearTimeout(timer);
  }, [onClose]);

  return (
    <div className="w-full max-w-2xl mx-auto mb-6">
      <div className="bg-emerald-50 border border-emerald-200 p-4 rounded-xl shadow-sm flex items-start space-x-3 animate-in fade-in slide-in-from-top-4 duration-300">
        <CheckCircle2 className="text-emerald-500 mt-0.5 flex-shrink-0" size={20} />
        <div className="flex-1">
          <h4 className="text-emerald-800 font-medium">Success</h4>
          <p className="text-emerald-600 text-sm mt-1">{message}</p>
        </div>
        <button
          onClick={onClose}
          className="text-emerald-400 hover:text-emerald-600 hover:bg-emerald-100 p-1.5 rounded-full transition-colors"
        >
          <X size={18} />
        </button>
      </div>
    </div>
  );
};

export default SuccessAlert;
