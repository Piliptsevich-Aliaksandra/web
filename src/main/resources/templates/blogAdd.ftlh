<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5 mb-5">
        <h1>New Post</h1>
        <form method="post">
            <input type="text" name="title" placeholder="Write post title" class="form-control"  value="${postForm.title!}">
            <@c.error "postForm.title"/><br>
            <textarea name="text" placeholder="Write post text" class="form-control">${postForm.text!}</textarea>
            <@c.error "postForm.text"/><br>
            <button type="submit" class="btn btn-success">Add Post</button>
        </form>
    </div>
</@c.page>