import React from 'react'
import Link from 'next/link';
import Image from 'next/image';

export default function ProductCard(props) {
    const {product} = props;
    const {
        id: price_id,
        unit_amount: cost,
        product: productInfo
    } = product;
    const { name, description} = productInfo;

    return (
        <Link href={`/product/${price_id}`}
        className='flex flex-col shadow bg-white hover:shadow-lg cursor-pointer'
        >
            <Image width={500} height={400} src={productInfo.images[0]} alt={name} className='w-full h-full object-cover gap-4' />
            <div className='flex flex-col gap-2 p-4'>
                <div className='flex items-center justify-between'>
                    <h3>{name}</h3>
                    <p>${cost/100}</p>
                </div>
                <p className='text-sm'>{description}</p>
            </div>
        </Link>
    )
}
