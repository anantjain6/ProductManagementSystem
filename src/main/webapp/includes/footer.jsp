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

    function renderProducts(products) {
        const container = document.getElementById("products");
        products.forEach(product => {
            container.innerHTML += productTemplate(product);
        });
    }

    function productTemplate(product) {
        return `
            <tr>
                <th scope="row">`+product.productId+`</th>
                <td>`+product.productName+`</td>
                <td>`+product.productPrice+`</td>
                <td>`+product.productQty+`</td>
                <td>
                    <a href='#' class="btn btn-success" onclick="">Edit</a>
                    <a href='#' class="btn btn-danger" onclick="">Delete</a>
                </td>
            </tr>`;
    }

    function loadProducts() {
        const request = new XMLHttpRequest();
        const url = "/admin/product/list"
        request.open('GET', url, true);
        console.log("Calling home...");
        request.send();
        request.onreadystatechange = () => {
            if (request.readyState === 4 && request.status === 200) {
                console.log(JSON.parse(request.response));
                renderProducts(JSON.parse(request.response));
            }
        }
    }

    loadProducts();
</script>
