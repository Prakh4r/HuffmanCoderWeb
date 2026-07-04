import React from 'react';

const Hero: React.FC = () => {
  return (
    <div className="text-center mb-10 mt-12 px-4">
      <h1 className="text-5xl font-extrabold text-slate-900 tracking-tight mb-4">
        Huffman<span className="text-brand-600">Coder</span>
      </h1>
      <p className="text-xl text-slate-600 max-w-2xl mx-auto font-light">
        Lossless File Compression using Huffman Coding
      </p>
      <p className="mt-4 text-slate-500 max-w-xl mx-auto text-sm leading-relaxed">
        Reduce your file sizes without losing a single bit of information. 
        Huffman coding provides an elegant, optimal prefix algorithm for lossless data compression.
      </p>
    </div>
  );
};

export default Hero;
