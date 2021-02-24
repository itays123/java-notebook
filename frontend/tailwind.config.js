module.exports = {
  purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        'cream-light': '#fffffb',
        'cream': '#d7ccc8',
        'cream-dark': '#a69b97',
        'cream-darker': '#776d69'
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
