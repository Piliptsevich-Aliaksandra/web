<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <div class="alert alert-info mt-2">
            <h3>${post.title}
                <#if user?? && user.id == post.user.id>
                </#if>
            </h3>
            <div class="mb-1 text-muted">${post.getFormattedCreationTime()} by <a
                        href="/user/${post.user.id}">${post.user.login}</a></div>
            <p>${post.text}</p>


            <header class="d-flex flex-wrap justify-content-center">
                <div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                    <div class="btn-group">
                        <button type="button" class="btn btn-outline-secondary"
                                onclick="location.href = '/blog/' + ${post.id} + '/like'">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-caret-up" viewBox="0 0 16 16">
                                <#if userRate?? && userRate>
                                    <path d="m7.247 4.86-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 0 0 .753-1.659l-4.796-5.48a1 1 0 0 0-1.506 0z"></path>
                                <#else>
                                    <path d="M3.204 11h9.592L8 5.519 3.204 11zm-.753-.659 4.796-5.48a1 1 0 0 1 1.506 0l4.796 5.48c.566.647.106 1.659-.753 1.659H3.204a1 1 0 0 1-.753-1.659z"></path>
                                </#if>
                            </svg>
                            <span class="visually-hidden">Button</span>
                        </button>
                        <div class="container ml-5 mr-5">${postRating}</div>
                        <button type="button" class="btn btn-outline-secondary"
                                onclick="location.href = '/blog/' + ${post.id} + '/dislike'">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-caret-down" viewBox="0 0 16 16">
                                <#if userRate?? && !userRate>
                                    <path d="M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z"></path>
                                <#else>
                                    <path d="M3.204 5h9.592L8 10.481 3.204 5zm-.753.659 4.796 5.48a1 1 0 0 0 1.506 0l4.796-5.48c.566-.647.106-1.659-.753-1.659H3.204a1 1 0 0 0-.753 1.659z"></path>
                                </#if>
                            </svg>
                            <span class="visually-hidden">Button</span>
                        </button>
                    </div>
                </div>
                <#if user?? && user.id==post.user.id>
                <ul class="nav nav-pills">
                    <li class="nav-item"><a href="/blog/${post.id}/edit" class="btn btn-warning m-l">Edit</a></li>
                    <li class="nav-item"><a href="/blog/${post.id}/delete" class="btn btn-warning m-l">Delete</a></li>
                </ul>
                </#if>
            </header>
        </div>
    </div>


    <div class="container mt-5 mb-5">
        <#if user??>
            <form method="post">
                <textarea name="text" placeholder="Write comment text" class="form-control">${commentForm.text!}</textarea>
                <@c.error "commentForm.text"/><br>
                <button type="submit" class="btn btn-success">Add a comment</button>
            </form>
        </#if>
        <#list post.comments as comment>
            <@c.postComment comment=comment/>
        </#list>
    </div>
</@c.page>