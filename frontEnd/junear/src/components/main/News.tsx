interface NewsData {
  industry: string;
  title: string;
  origin_url: string;
  image_url: string;
  times: string;
  media: string;
}

function News({ title, industry, origin_url, image_url, times, media }: NewsData) {
  return (
    <div className="h-[360px] w-[250px]">
      <a href={origin_url} target="_blank" rel="noopener noreferrer" onClick={(e) => e.stopPropagation()}>
        <img src={image_url} alt="JUNEAR-3-2" className="h-[200px] w-[100%] rounded-[20px]" />
      </a>
      <div className="text-white mt-[20px]">
        <a href={origin_url} target="_blank" rel="noopener noreferrer" onClick={(e) => e.stopPropagation()}>
          {title}
        </a>
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
