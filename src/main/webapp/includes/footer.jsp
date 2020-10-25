<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<footer class="footer fixed-bottom py-3 bg-dark text-center">
    <div class="container">
        <span class="text-muted">Copyright Reserver &copy; 2020</span>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

<script type="text/javascript">
    //Get the button:
    mybutton = document.getElementById("myBtn");

    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function () {
        scrollFunction()
    };

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
        document.body.scrollTop = 0; // For Safari
        document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    }

    const renderProducts = (products) => {
        const container = document.getElementById("products");
        if (container) {
            container.innerHTML = '';
            products.forEach(product => {
                container.innerHTML += productTemplate(product);
            });
        }
    }

    const productTemplate = (product) => {
        return `
            <tr>
                <th scope="row">`+product.productId+`</th>
                <td>`+product.productName+`</td>
                <td>`+product.productPrice+`</td>
                <td>`+product.productQty+`</td>
                <td>
                    <a href="/admin/product/update/`+product.productId+`" class="btn btn-success">Edit</a>
                    <a href='#' class="btn btn-danger" onclick="deleteProduct(`+product.productId+`)">Delete</a>
                </td>
            </tr>`;
    }

    const loadProducts = () => {
        const request = new XMLHttpRequest();
        const url = "/admin/product/list"
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = () => {
            if (request.readyState === 4 && request.status === 200) {
                renderProducts(JSON.parse(request.response));
            } else {
               // TODO 404
            }
        }
    }

    const deleteProduct = (id) => {
        if (confirm("Do you really want to delete the product?")) { // TODO
            if (Number.isSafeInteger(id)) {
                const request = new XMLHttpRequest();
                const url = "/admin/product/delete/" + id;
                request.open('DELETE', url, true);
                request.onreadystatechange = () => {
                    if (request.readyState === 4 && request.status === 204) {
                        displaySuccess("Success.");
                    }
                    loadProducts();
                }
                request.send();
            }
        }
    }

    const fetchProduct = (id) => {
        const request = new XMLHttpRequest();
        const url = "/admin/product/" + parseInt(id);
        request.open('GET', url, true);
        request.onreadystatechange = () => {
            if (request.readyState === 4 && request.status === 200) {
                const product = JSON.parse(request.response);
                const productFormHolder = document.getElementById("productFormHolder");
                if (productFormHolder) {
                    productFormHolder.innerHTML = populateForm(product);
                    const title = document.getElementById("productFormTitle");
                    title.innerText = "Edit product";
                    const productCategory = document.getElementById("productCategory");
                    if (productCategory) {
                        const categories = fetchCategories();
                        productCategory.innerHTML += renderCategories(categories);
                    }
                    const form = document.getElementById("productForm");
                    form.addEventListener("submit", submitProduct(id));
                }
            }
        }
        request.send();
    }

    const populateForm = (product) => {
        return `
            <form action="#" id="productForm">
                <div class="form-group">
                    <label for="productName" class="control-label">Product Name</label>
                    <input type="text"
                        id="productName"
                        class="form-control"
                        required minlength="3"
                        value="`+product.name+`"/>
                    <span id="productNameErrorMessage" class="form-text text-muted"></span>
                </div>
                <div class="form-group">
                    <label for="productPrice" class="control-label">Product Price</label>
                    <input type="text"
                        id="productPrice"
                        class="form-control"
                        required min="0.1"
                        pattern="[0-9]*\\.?[0-9]+"
                        value="`+product.price+`"/>
                    <span id="productPriceErrorMessage" class="form-text text-muted"></span>
                </div>
                <div class="form-group">
                    <label for="productQuantity" class="control-label">Product Qty</label>
                    <input type="text"
                        id="productQuantity"
                        class="form-control"
                        required
                        value="`+product.quantity+`"/>
                    <span id="productQtyErrorMessage" class="form-text text-muted"></span>
                </div>
                <div class="form-group">
                    <label for="productCategory" class="control-label">Product Category</label>
                    <select id="productCategory" class="form-control">

                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success btn-lg btn-block" value="add/edit">
                </div>
            </form>
        `
    }

    const renderCategories = (categories) => {
        const select = document.getElementById("productCategory");
        if (select && categories) {
            select.innerHTML = "";
            categories.forEach(category => {
                select.innerHTML += `<option value="`+category.id+`">`+category.name+`</option>`
            });
        }
    }

    const fetchCategories = () => {
        const request = new XMLHttpRequest();
        const url = "/admin/product/categories";
        request.open('GET', url, true);
        request.onreadystatechange = () => {
            console.log(request);
            if (request.readyState === 4 && request.status === 200) {
                renderCategories(JSON.parse(request.response));
            }
        }
        request.send();
    }

    const submitProduct = (id) => {
        return (e) => {
            e.preventDefault();
            const product = {
                "id": id,
                "name": document.getElementById("productName").value,
                "price": document.getElementById("productPrice").value,
                "quantity": document.getElementById("productQuantity").value,
                "category": document.getElementById("productCategory").value,
            }
            console.log(JSON.stringify(product));
            const request = new XMLHttpRequest();
            const url = "/admin/product/update";
            request.open('PUT', url, true);
            request.setRequestHeader("Content-Type", "application/json");
            request.onreadystatechange = () => {
                if (request.readyState === 4) {
                    if (request.status === 200) {
                        displaySuccess("Product updated successfully.");
                        return;
                    }
                    if (request.status === 404) {
                        displayError("Product not found");
                    }
                }

            }
            request.send(JSON.stringify(product));
        }
    }

    const displaySuccess = message => {
        const successMessage = document.getElementById("successMsg");
        if (successMessage) {
            successMessage.innerText = message;
            hideElement(successMessage);
            showElement(successMessage);
        }
    }

    const displayError = message => {
        const errorMessage = document.getElementById("errorMsg");
        if (errorMessage) {
            errorMessage.innerText = message;
            hideElement(errorMessage);
            showElement(errorMessage);
        }
    }

    const showElement = (element) => {
        if (element) {
            element.style.display = 'block';
        }
    }

    const hideElement = (element) => {
        if (element) {
            element.style.display = 'none';
        }
    }

    loadProducts();
</script>
