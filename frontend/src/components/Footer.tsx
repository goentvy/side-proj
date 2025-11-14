const Footer = () => {
  return (
    <footer className="bg-gray-100 text-gray-600 text-sm py-4 px-6 border-t mt-10">
      <div className="max-w-7xl mx-auto flex flex-col sm:flex-row justify-between items-center gap-2">
        <div>
          ⓒ {new Date().getFullYear()} Entvy - 공매 서비스
        </div>
        <div className="text-center sm:text-right">
          본 서비스는 <strong>온비드 공공 API</strong>를 활용하여 제공되며,  
          <a
            href="https://www.onbid.co.kr"
            target="_blank"
            rel="noopener noreferrer"
            className="text-blue-600 hover:underline ml-1"
          >
            온비드 공식 홈페이지
          </a>
          의 데이터를 기반으로 합니다. <br />
          문의: <a href="mailto:contact@onbid-service.kr" className="text-blue-600 hover:underline">contact@onbid-service.kr</a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;