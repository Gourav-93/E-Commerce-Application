document.addEventListener('DOMContentLoaded', () => {
    const navbarContainer = document.getElementById('navbar-container');
    if (!navbarContainer) return;

    const renderNavbar = () => {
        const isAuthenticated = AuthUtils.isAuthenticated();
        const user = AuthUtils.getUser();

        const authLinks = isAuthenticated ? `
            <a href="profile.html" class="nav-link">Profile</a>
            <a href="orders.html" class="nav-link">Orders</a>
            <a href="wishlist.html" class="nav-link">Wishlist</a>
            <a href="cart.html" class="nav-link">Cart</a>
            <button id="logout-btn" class="btn btn-outline">Logout</button>
        ` : `
            <a href="login.html" class="nav-link">Login</a>
            <a href="register.html" class="btn btn-primary">Sign Up</a>
        `;

        navbarContainer.innerHTML = `
            <nav class="navbar">
                <div class="container">
                    <a href="index.html" class="nav-brand gradient-text">NexGen Store</a>
                    <div class="nav-links">
                        <a href="index.html" class="nav-link">Home</a>
                        <a href="products.html" class="nav-link">Products</a>
                        <a href="categories.html" class="nav-link">Categories</a>
                    </div>
                    <div class="nav-actions">
                        ${authLinks}
                    </div>
                </div>
            </nav>
        `;

        if (isAuthenticated) {
            document.getElementById('logout-btn')?.addEventListener('click', () => {
                AuthUtils.logout();
            });
        }
    };

    renderNavbar();
});
