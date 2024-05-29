const apiUrl = 'http://localhost:8888/eligere/books';

const isbnField = document.getElementById('isbn');
const titleField = document.getElementById('title');
const authorField = document.getElementById('author');
const publicationDateField = document.getElementById('publicationDate');

// Load books on page load
document.addEventListener('DOMContentLoaded', fetchBooks);

// Add book form submission
document.getElementById('bookForm').addEventListener('submit', addBook);

async function fetchBooks() {
    const response = await fetch(apiUrl);
    const books = await response.json();
    console.log(books);
    const booksList = document.getElementById('booksList');
    booksList.innerHTML = '';

    books.forEach(book => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${book.bookId}</td>
            <td>${book.isbn}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.publicationDate}</td>
            <td class="actions">
                <button class="edit" onclick="editBook('${book.bookId}')">Edit</button>
                <button class="delete" onclick="deleteBook('${book.bookId}')">Delete</button>
            </td>
        `;
        booksList.appendChild(row);
    });
}

async function addBook(event) {
    event.preventDefault();
    const isbn = isbnField.value;
    const title = titleField.value;
    const author = authorField.value;
    const publicationDate = publicationDateField.value;

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ isbn, title, author, publicationDate })
    });

    if (response.ok) {
        fetchBooks();
        document.getElementById('bookForm').reset();
    } else {
        alert('Failed to add book');
    }
}

async function deleteBook(bookId) {
    const response = await fetch(`${apiUrl}/${bookId}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        fetchBooks();
    } else {
        alert('Failed to delete book');
    }
}

function editBook(bookId) {
    const isbn = prompt('Enter new ISBN:');
    const title = prompt('Enter new title:');
    const author = prompt('Enter new author:');
    const publicationDate = prompt('Enter new publication date (YYYY-MM-DD):');

    updateBook(bookId, { isbn, title, author, publicationDate });
}

async function updateBook(bookId, updatedBook) {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
    "isbn": updatedBook.isbn,
    "title": updatedBook.title,
    "author": updatedBook.author,
    "publicationDate": updatedBook.publicationDate
    });

    const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };

    fetch(`${apiUrl}/${bookId}`, requestOptions)
    .then((response) => response.text())
    .then((result) => {
        console.log(result);
        alert("Book Infomation Updated successfully!");
        fetchBooks();
    })
    .catch((error) => console.error(error));

}

