import Header from '@/components/HomePageComponents/Header'
import './globals.css'
import { Inter } from 'next/font/google'
import Footer from '@/components/HomePageComponents/Footer'

const inter = Inter({ subsets: ['latin'] })

export const metadata = {
  title: 'E-Gommerce',
  description: 'E-Gommerce Online Store Project',
}

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <head>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossOrigin="anonymous" referrerPolicy="no-referrer" />
      </head>
      <body className={'min-h-screen flex flex-col relative' + inter.className}>
        <Header />
        <div className='flex-1'>
          {children}
        </div>
        <Footer />
      </body>
    </html>
  )
}
