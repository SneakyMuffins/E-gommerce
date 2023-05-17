import Link from "next/link";

export default function Header() {
    return (
      <header className="sticky top-0 p-6 bg-white border-b border-solid border-blue-900 shadow-md z-50 text-2xl sm:text-3xl md:text-4xl sm-p8 flex item-center justify-between">
        <Link href={'/'}>
          <h1 className='uppercase cursor-pointer hover:scale-110'>E-Gommerce</h1>
        </Link>
        <div className="flex gap-[50px]">
          <Link href={'/login'}>
            <i className="fa-solid cursor-pointer hover:text-slate-500 fa-right-to-bracket"></i>
          </Link>
          <Link href={'/cart'}>
            <i className="fa-solid cursor-pointer hover:text-slate-500 fa-cart-shopping"></i>
          </Link>
        </div>
      </header>
    )
  }
  