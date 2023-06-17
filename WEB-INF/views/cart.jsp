
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%@ page import="eStoreProduct.utility.ProductStockPrice" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
     <style>
        .product-box {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }
        
        .product-card {
            width: 30%;
            margin-bottom: 20px;
        }
        
        #costdiv {
            text-align: center;
            margin-top: 40px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).on('change', '.qtyinp', function() {
            updateTotalCost();
        });

        function updateTotalCost() {
            var totalCost = 0;

            $('.qtyinp').each(function() {
                var quantity = parseInt($(this).val(), 10);
                var price = parseFloat($(this).closest('.card-body').find('.prodPrice').text());

                if (!isNaN(quantity) && !isNaN(price)) {
                    totalCost += quantity * price;
                }
            });

            $('#cst').text('TotalCost: $' + totalCost.toFixed(2));
        }

        function updateQuantity(element) {
            var quantity = parseInt(element.value, 10);
            var price = parseFloat($(element).closest('.card-body').find('.prodPrice').text());

            if (!isNaN(quantity) && !isNaN(price)) {
                var productCost = quantity * price;
                $(element).closest('.card-body').find('.prodCost').text('Product Cost: $' + productCost.toFixed(2));
                updateTotalCost();
            }
        }

        $(document).ready(function() {
            updateTotalCost();
        });

        $(document).on('click', '.buyid', function(event) {
            event.preventDefault();
            buynow();
        });

        function buynow() {
            console.log("buy now");
            window.location.href = "buycartitems";
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2>Cart</h2>
        <div class="row mt-4">
            <%-- Iterate over the products and render the HTML content --%>
            <%
            
                List<ProductStockPrice> products = (List<ProductStockPrice>) request.getAttribute("products");
           double cartcost=0;
            int totalitems=0;
            int shipch=0;
           for (ProductStockPrice product : products) {totalitems++;
                %>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100">
                    <img class="card-img-top" src="<%= product.getImage_url() %>" alt="<%= product.getProd_title() %>">
                    <br>
                    
                    <div class="card-body">
                    <label>Quantity<input type="number" align="center" name="Quantity" class="form-control qtyinp" style="width: 50px;" min="1" data-product-id="<%= product.getProd_id() %>" onchange="updateQuantity(this)" value="1"></label>
                    
                        <h5 class="card-title"><%= product.getProd_title() %></h5>
                    <p class="card-text"><%= product.getProd_desc() %></p>
                    <p class="card-text"><%= product.getPrice() %></p> 
                        <button class="btn btn-primary removeFromCart" data-product-id="<%= product.getProd_id() %>">Remove from Cart</button>
                        <button class="btn btn-secondary addToWishlistButton" data-product-id="<%= product.getProd_id() %>">Add to Wishlist</button>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            
        </div>
    </div>
    <div>
            <div id="costdiv">
            <p>Total Items:<%=totalitems %></p>
            <p>ShipMent Charges:<%=shipch %></p>
            
            
            <div >
            <%
            if(products!=null)
            {
            	%>
            	<p id="cst">TotalCost:$<%=cartcost%></p>
            	<button class="buyid" style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; cursor: pointer;">Buy Now</button>

            	<%
            }
            	else
            	{
            		%><p>No CartProducts</p>
            		<%
            }
            %></div></div>
            </div>
</body>
</html>