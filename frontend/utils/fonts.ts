import { Poppins } from 'next/font/google';

export const poppins = Poppins({
    weight: ['200', '300', '400', '700', '800'],
    variable: '--font-poppins',
    subsets: ['latin'],
});