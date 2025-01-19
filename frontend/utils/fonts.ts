import { Poppins, Orbitron } from 'next/font/google';

export const poppins = Poppins({
    weight: ['200', '300', '400', '700', '800'],
    variable: '--font-poppins',
    subsets: ['latin'],
});

export const orbitron = Orbitron({
    weight: ['400', '700', '800'],
    variable: '--font-orbitron',
    subsets: ['latin'],
});