/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

window.addEventListener('scroll', () => {
  const header = document.querySelector('.hdr');
  const logo_container = document.querySelector('.logo-container');
  const company_name = document.querySelector('.company-name');
  const nav_links = document.querySelector('.hdr');
  const scrolledHeight = window.innerHeight * 0.1; // 10vh

  if (window.scrollY > scrolledHeight) {
    header.classList.add('scrolled');
    logo_container.classList.add('logo-container-scroll');
    company_name.classList.add('company_name_scroll');
    nav_links.classList.add('hdr_scroll');
  } else {
    header.classList.remove('scrolled');
    logo_container.classList.remove('logo-container-scroll');
    company_name.classList.remove('company_name_scroll');
    nav_links.classList.remove('hdr_scroll');
  }
});