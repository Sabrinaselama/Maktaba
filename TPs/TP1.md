# TP1: Introduction to MVVM Architecture and Book Model

**Course:** Mobile Application Development  
**University:** El Oued University (جامعة الوادي)  
**Level:** 3rd Year Computer Science  
**Duration:** 3 hours  
**Topic:** Understanding MVVM Architecture Pattern

---

## 🎯 Learning Objectives

By the end of this practical session, you will be able to:

1. ✅ Understand the **MVVM (Model-View-ViewModel)** architecture pattern
2. ✅ Identify and explain the role of each layer in MVVM
3. ✅ Create and manipulate **data models** in Kotlin
4. ✅ Work with **repositories** for data management
5. ✅ Understand **use cases** in Clean Architecture
6. ✅ Use **ViewModels** to manage UI state
7. ✅ Build UI screens with **Jetpack Compose**
8. ✅ Understand **StateFlow** for reactive programming

---

## 📚 Theory: MVVM Architecture

### What is MVVM?

**MVVM** stands for **Model-View-ViewModel**. It's an architectural pattern that separates the user interface (UI) from the business logic and data.

```
┌─────────────────────────────────────────────────┐
│                     View (UI)                   │
│  (Jetpack Compose Screens - BookListScreen.kt) │
└────────────────┬────────────────────────────────┘
                 │ observes StateFlow
                 ↓
┌─────────────────────────────────────────────────┐
│                  ViewModel                       │
│         (BookViewModel.kt)                       │
│  • Holds UI State                                │
│  • Calls Use Cases                               │
└────────────────┬────────────────────────────────┘
                 │ calls
                 ↓
┌─────────────────────────────────────────────────┐
│              Use Case (Domain)                   │
│         (GetBooksUseCase.kt)                     │
│  • Contains Business Logic                       │
└────────────────┬────────────────────────────────┘
                 │ calls
                 ↓
┌─────────────────────────────────────────────────┐
│              Repository (Data)                   │
│         (BookRepository.kt)                      │
│  • Manages Data Sources                          │
└────────────────┬────────────────────────────────┘
                 │ returns
                 ↓
┌─────────────────────────────────────────────────┐
│                Model (Data)                      │
│              (Book.kt)                           │
│  • Data Structure                                │
└─────────────────────────────────────────────────┘
```

### Layer Responsibilities

#### 1. **Model (Data Layer)**
- **File:** `Book.kt`
- **Purpose:** Defines the structure of data
- **Example:** A `Book` has `isbn`, `title`, and `nbPages`

#### 2. **Repository (Data Layer)**
- **File:** `BookRepository.kt`
- **Purpose:** Manages data sources (database, API, local lists)
- **Example:** Provides a list of books

#### 3. **Use Case (Domain Layer)**
- **File:** `GetBooksUseCase.kt`
- **Purpose:** Contains business logic
- **Example:** Gets books from repository and applies any business rules

#### 4. **ViewModel (Presentation Layer)**
- **File:** `BookViewModel.kt`
- **Purpose:** Prepares data for the UI and manages UI state
- **Example:** Loads books and exposes them as StateFlow

#### 5. **View (Presentation Layer)**
- **File:** `BookListScreen.kt`
- **Purpose:** Displays the UI
- **Example:** Shows a list of books using Jetpack Compose

---

## 📂 Project Structure

After completing this TP, your project structure looks like this:

```
app/src/main/java/com/ElOuedUniv/maktaba/
├── data/
│   ├── model/
│   │   └── Book.kt                    # Data model
│   └── repository/
│       └── BookRepository.kt          # Data source management
├── domain/
│   └── usecase/
│       └── GetBooksUseCase.kt         # Business logic
├── presentation/
│   ├── screens/
│   │   └── BookListScreen.kt          # UI screen
│   ├── viewmodel/
│   │   └── BookViewModel.kt           # UI state management
│   └── theme/
│       └── ...                        # Theme files
└── MainActivity.kt                    # App entry point
```

---

## 💻 Practical Exercises

### Exercise 1: Complete Book Information (30 minutes)

**Location:** Open `BookRepository.kt`

You will see a list of 5 books with incomplete information:

```kotlin
private val booksList = listOf(
    Book(isbn = "", title = "Clean Code", nbPages = 0),
    Book(isbn = "", title = "The Pragmatic Programmer", nbPages = 0),
    // ... more books
)
```

**Your Task:**

1. Complete the **ISBN** and **number of pages** for each book
2. You can find ISBN numbers on:
   - [Google Books](https://books.google.com/)
   - [Amazon](https://www.amazon.com/)
   - [GoodReads](https://www.goodreads.com/)

**Example of completed book:**

```kotlin
Book(
    isbn = "978-0-13-235088-4",
    title = "Clean Code",
    nbPages = 464
)
```

**ISBN Format:**
- Use ISBN-13 format (starts with 978 or 979)
- Format: `978-X-XX-XXXXXX-X`

---

### Exercise 2: Add More Books (30 minutes)

**Location:** `BookRepository.kt` (same file)

**Your Task:**

Add **5 more books** to the `booksList`. Choose books related to:
- Computer Science
- Programming
- Algorithms
- Databases
- Mobile Development
- Or any topic you're interested in!

**Requirements:**
- Each book must have a valid ISBN
- Each book must have a title
- Each book must have the number of pages

**Example:**

```kotlin
Book(
    isbn = "978-0-262-03384-8",
    title = "Introduction to Algorithms",
    nbPages = 1312
)
```

---

### Exercise 3: Run and Test the Application (15 minutes)

1. **Build the project:**
   - Click on "Build" → "Make Project" in Android Studio
   - Or use: `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)

2. **Run the application:**
   - Click the green "Run" button
   - Or use: `Shift+F10` (Windows/Linux) or `Ctrl+R` (Mac)

3. **Verify the results:**
   - You should see a list of books in the app
   - Each book card should display:
     - Title (in bold)
     - ISBN
     - Number of pages

---

### Exercise 4: Code Analysis (30 minutes)

**Analyze the following files and answer the questions:**

#### 4.1 Book Model (`Book.kt`)

**Questions:**
1. What is a `data class` in Kotlin?
2. Why do we use `val` instead of `var` for the properties?
3. What would happen if we used `var` instead?

#### 4.2 Repository (`BookRepository.kt`)

**Questions:**
1. What is the purpose of the `BookRepository` class?
2. What does the `getAllBooks()` function return?
3. How would you add a function to search books by title?

**Challenge:** Write a function `searchBooksByTitle(query: String)` that finds books containing the query string (hint: use `.filter { }`).

#### 4.3 Use Case (`GetBooksUseCase.kt`)

**Questions:**
1. Why do we have a separate Use Case instead of calling the repository directly from the ViewModel?
2. What is the `operator fun invoke()` syntax?
3. How would you add business logic to filter only books with more than 300 pages?

#### 4.4 ViewModel (`BookViewModel.kt`)

**Questions:**
1. What is `StateFlow` and why do we use it?
2. What is the difference between `_books` (private) and `books` (public)?
3. What is `viewModelScope` and why do we use it?
4. When is the `init` block executed?

#### 4.5 View (`BookListScreen.kt`)

**Questions:**
1. How does the screen observe changes in the ViewModel?
2. What is `LazyColumn` and why do we use it instead of `Column`?
3. What happens when the books list is empty?

---

## 🔍 Deep Dive: Understanding the Flow

Let's trace what happens when the app starts:

```
1. MainActivity.onCreate()
   ↓
2. Creates BookRepository
   ↓
3. Creates GetBooksUseCase(repository)
   ↓
4. Creates BookViewModel(useCase)
   ↓
5. BookViewModel.init { }
   ↓
6. loadBooks()
   ↓
7. getBooksUseCase()
   ↓
8. bookRepository.getAllBooks()
   ↓
9. Returns List<Book>
   ↓
10. _books.value = bookList (StateFlow update)
   ↓
11. BookListScreen observes the change
   ↓
12. UI recomposes and displays books
```

---

## 🧪 Bonus Exercises (Optional)

### Bonus 1: Add a Book Counter

**Task:** Modify `BookListScreen.kt` to display the total number of books at the top of the screen.

**Hint:**
```kotlin
Text(text = "Total Books: ${books.size}")
```

### Bonus 2: Calculate Total Pages

**Task:** Create a function in `BookViewModel.kt` to calculate the total number of pages of all books combined.

**Hint:**
```kotlin
val totalPages: Int = books.value.sumOf { it.nbPages }
```

Display this in the UI below the book count.

### Bonus 3: Filter Books by Page Count

**Task:** Add a function to the repository that returns only books with more than 400 pages.

```kotlin
fun getLongBooks(): List<Book> {
    return booksList.filter { it.nbPages > 400 }
}
```

---

## 📝 How to Submit Your Work

### Submission Method: GitHub Pull Request

For this TP, you will submit your work using **Git and GitHub** by creating a **Pull Request** to the course repository. This is how professional developers collaborate on real-world projects!

---

### 🎓 Step 1: Learn Git & GitHub (Optional for Beginners)

If you're new to Git and GitHub, **start here first**:

**📚 GitByBit - Interactive Git Tutorial**  
Learn Git basics interactively: **[https://gitbybit.com/](https://gitbybit.com/)**

This interactive tutorial will teach you:
- Git basics (clone, commit, push)
- How to create branches
- How to make Pull Requests
- GitHub workflow fundamentals

---

### 🚀 Step 2: Complete the Exercises

1. **Clone the repository** (provided by your professor)
   ```bash
   git clone [repository-url]
   cd Maktaba
   ```

2. **Create a new branch** with your name
   ```bash
   git checkout -b tp1-yourname
   ```

3. **Complete Exercise 1:** Fill in book information in `BookRepository.kt`
   - Add ISBN and page numbers for the 5 starter books

4. **Complete Exercise 2:** Add 5 more books to the repository

5. **Commit your changes**
   ```bash
   git add app/src/main/java/com/ElOuedUniv/maktaba/data/repository/BookRepository.kt
   git commit -m "TP1: Complete book information and add 5 new books"
   ```

6. **Bonus Exercises (Optional):**
   - Implement `searchBooksByTitle()` function
   - Add book counter feature
   - Calculate total pages
   - Commit each bonus exercise separately

---

### 📤 Step 3: Push Your Changes

Push your branch to GitHub:

```bash
git push origin tp1-yourname
```

---

### 🔀 Step 4: Create a Pull Request

1. Go to the course repository on GitHub
2. Click **"Pull requests"** tab
3. Click **"New pull request"**
4. Select your branch (`tp1-yourname`)

---

## 📖 Additional Resources

### Git & GitHub Resources
- **[GitByBit](https://gitbybit.com/)** - Interactive Git learning (Highly Recommended!)
- [GitHub Docs - Pull Requests](https://docs.github.com/en/pull-requests)
- [Git Cheat Sheet](https://education.github.com/git-cheat-sheet-education.pdf)

### Kotlin Resources
- [Kotlin Official Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlin Data Classes](https://kotlinlang.org/docs/data-classes.html)

### Android Resources
- [Android MVVM Guide](https://developer.android.com/topic/architecture)
- [Jetpack Compose Basics](https://developer.android.com/jetpack/compose/tutorial)
- [StateFlow Documentation](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)ل

