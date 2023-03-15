<#import "/spring.ftl" as spring/>

<#macro page>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
    </head>
    <body>
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                <use xlink:href="#bootstrap"></use>
            </svg>
        </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="/blog" class="nav-link px-2 link-dark">Blog</a></li>

            <#if user??>
                <li><a href="/blog/add/${user.id}" class="nav-link px-2 link-dark">Add Post</a></li>
            </#if>
            <li><a href="/about" class="nav-link px-2 link-dark">About</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <#if user??>
                <button type="button" class="btn btn-outline-primary me-2"
                        onclick="location.href = '/user/' + ${user.id}">${user.login}</button>
                <button type="button" class="btn btn-primary" onclick="location.href='/logout'">Logout</button>
            <#else>
                <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/enter'">Login
                </button>
                <button type="button" class="btn btn-primary" onclick="location.href='/register'">Sign-up</button>
            </#if>
        </div>
    </header>
    <#nested/>
    <div fragment="footer">
        <footer class="text-muted py-5">
            <div class="container">
                <p class="float-end mb-1">
                    <a href="#">Back to top</a>
                </p>
                <p class="mb-1">Album example is © Bootstrap, but please download and customize it for yourself!</p>
                <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a
                            href="/docs/5.3/getting-started/introduction/">getting started guide</a>.</p>
            </div>
        </footer>
    </div>
    </body>
    </html>
</#macro>







<#macro post post>
    <div class="alert alert-info mt-2">
        <h3>${post.title}</h3><br><br>
        <p>${post.text}</p>
        <a href="/blog/${post.id}" class="btn btn-warning">Details...</a>
    </div>
</#macro>





<#macro userComment comment>
    <div class="mb-1 text-muted">${comment.getFormattedCreationTime()} on <a
                href="/blog/${comment.post.id}">${comment.post.title}</a></div>
    <p>${comment.text}</p>
</#macro>




<#macro postComment comment>
    <div class="mb-1 text-muted">${comment.getFormattedCreationTime()} by <a
                href="/user/${comment.user.id}">${comment.user.login}</a></div>
    <p>${comment.text}</p>
</#macro>

<#macro error field>
    <@spring.bind field/>
    <#if spring.status.errorMessage??>
        <div class="error">${spring.status.errorMessage}</div>
    </#if>
</#macro>