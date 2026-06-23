document.addEventListener('DOMContentLoaded', () => {
    const footerContainer = document.getElementById('footer-container');
    if (!footerContainer) return;

    footerContainer.innerHTML = `
        <footer style="margin-top: 4rem; padding: 3rem 0; background: var(--bg-surface-elevated); border-top: 1px solid var(--glass-border);">
            <div class="container" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 2rem;">
                <div>
                    <h3 style="margin-bottom: 1rem;" class="gradient-text">NexGen Store</h3>
                    <p style="color: var(--text-secondary);">Premium e-commerce experience tailored for modern shoppers.</p>
                </div>
                <div>
                    <h4 style="margin-bottom: 1rem;">Quick Links</h4>
                    <ul style="display: flex; flex-direction: column; gap: 0.5rem; color: var(--text-secondary);">
                        <li><a href="products.html">Shop</a></li>
                        <li><a href="categories.html">Categories</a></li>
                        <li><a href="login.html">Login</a></li>
                    </ul>
                </div>
                <div>
                    <h4 style="margin-bottom: 1rem;">Support</h4>
                    <ul style="display: flex; flex-direction: column; gap: 0.5rem; color: var(--text-secondary);">
                        <li><a href="#">Contact Us</a></li>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">Shipping</a></li>
                    </ul>
                </div>
            </div>
            <div style="text-align: center; margin-top: 3rem; padding-top: 1.5rem; border-top: 1px solid var(--glass-border); color: var(--text-muted);">
                &copy; ${new Date().getFullYear()} NexGen Store. All rights reserved.
            </div>
        </footer>
    `;
});
