import React from 'react';
import { Minimize2, Maximize2 } from 'lucide-react';

interface ActionButtonsProps {
  onCompress: () => void;
  onDecompress: () => void;
  disabled: boolean;
}

const ActionButtons: React.FC<ActionButtonsProps> = ({ onCompress, onDecompress, disabled }) => {
  return (
    <div className="flex flex-col sm:flex-row items-center justify-center gap-4 w-full max-w-2xl mx-auto">
      <button
        onClick={onCompress}
        disabled={disabled}
        className="group relative w-full sm:w-1/2 flex items-center justify-center px-6 py-4 bg-brand-600 text-white font-medium text-lg rounded-xl shadow-md hover:bg-brand-700 hover:shadow-lg disabled:opacity-50 disabled:cursor-not-allowed transition-all duration-300 overflow-hidden"
      >
        <div className="absolute inset-0 bg-white/20 translate-y-full group-hover:translate-y-0 transition-transform duration-300 ease-out" />
        <span className="relative flex items-center gap-2">
          <Minimize2 size={22} className="group-hover:scale-110 transition-transform duration-300" />
          Compress
        </span>
      </button>

      <button
        onClick={onDecompress}
        disabled={disabled}
        className="group relative w-full sm:w-1/2 flex items-center justify-center px-6 py-4 bg-slate-800 text-white font-medium text-lg rounded-xl shadow-md hover:bg-slate-900 hover:shadow-lg disabled:opacity-50 disabled:cursor-not-allowed transition-all duration-300 overflow-hidden"
      >
        <div className="absolute inset-0 bg-white/10 translate-y-full group-hover:translate-y-0 transition-transform duration-300 ease-out" />
        <span className="relative flex items-center gap-2">
          <Maximize2 size={22} className="group-hover:scale-110 transition-transform duration-300" />
          Decompress
        </span>
      </button>
    </div>
  );
};

export default ActionButtons;
