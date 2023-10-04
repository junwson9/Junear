import { ReactComponent as Logo } from 'assets/image/nav-logo.svg';

interface NewsProps {
  data: {
    image_url: string | null;
    title: string;
    media: string;
    times: string;
    origin_url: string;
  };
}

export default function NewsOne({ data }: NewsProps) {
  // console.log(data);
  const handleClick = () => {
    if (data.origin_url) {
      window.open(data.origin_url, '_blank', 'noopener,noreferrer');
    }
  };
  return (
    <div className="flex mx-4 mt-4 mb-4" onClick={handleClick} style={{ cursor: 'pointer' }}>
      {data.image_url ? (
        <img className="flex w-[120px] h-[120px] left-[25px] rounded-[10px]" src={data.image_url} />
      ) : (
        <Logo className="flex w-[120px] h-[120px] left-[25px] rounded-[10px]" />
      )}
      <div className="mx-4 my-auto">
        <div className="flex top-[29px] text-white text-lg text-left">{data.title}</div>
        <div className="mt-2 text-neutral-400 text-left">
          {data.media} | {data.times}
        </div>
      </div>
    </div>
  );
}
