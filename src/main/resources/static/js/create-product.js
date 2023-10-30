$(document).ready(function(){
    $('#fileMainImage').change(function(){
        showProductMainImage(this)
    });

    $('#fileExtraImage').change(function() {
        showProductExtraImage(this)
    });

    $(document).on('click', '.extra-image-delete', function(){
        deleteProductExtraImage($(this))
    });
});

function showProductMainImage(fileInput){
    let file = fileInput.files[0];
    let reader = new FileReader();
    reader.onload = function(e) {
        $('#productMainImage').attr('src', e.target.result);
    }
    reader.readAsDataURL(file);
}

function showProductExtraImage(filesInput){
    let files = filesInput.files;
    let extraImageContainer = $('#extraImageContainer');

    for(let i = 0; i < files.length; i++){
        let reader = new FileReader();
        let file = files[i];

        reader.onload = function(e) {
            let imageBlock = $('<div>');
            let image = $('<img>');
            let iconDelete = $('<i>');
            imageBlock.attr('class', 'image-content');
            image.attr('class', 'extra-image');
            iconDelete.attr('class', 'extra-image-delete fa-regular fa-circle-xmark');
            image.attr('src', e.target.result);
            imageBlock.append(image, iconDelete)
            extraImageContainer.append(imageBlock);
        }
        reader.readAsDataURL(file);
    }
}

function deleteProductExtraImage(deleteButton){
    let deleteImageBlock = deleteButton.closest('.image-content');
    deleteImageBlock.remove();
}

//Select
new MultiSelectTag('productSize');

new MultiSelectTag('productColor');