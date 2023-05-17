/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    appDir: true,
  }, images: {
    remotePatterns: [
      {
        hostname: 'files.stripe.com',
      },
    ],
  },
}

module.exports = nextConfig
