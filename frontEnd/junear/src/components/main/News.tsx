interface NewsData {
  industry: string;
  title: string;
  origin_url: string;
  image_url: string;
  times: string;
  media: string;
}
function News({ title, industry, origin_url, image_url, times, media }: NewsData) {
  const handleNewsClick = () => {
    // 클릭 시 origin_url로 이동
    window.location.href = origin_url;
  };
  return (
    <div className="h-[360px]  w-[250px] ">
      <button>
        <img src={image_url} alt="JUNEAR-3-2" className="h-[200px] w-[100%] rounded-[20px]" onClick={handleNewsClick} />
      </button>
      <div className="text-white mt-[20px]">
        <button className="mt-[10px]" onClick={handleNewsClick}>
          {title}
        </button>
        <div className="flex whitespace-nowrap text-xs mt-[20px] text-gray-400 gap-[20px] justify-center">
          <p>{industry}</p>
          <p>{media}</p>
          <p>{times}</p>
        </div>
      </div>
    </div>
  );
}
export default News;
