import React, { Component } from "react";
import { getAllPosts } from "../../util/APIUtils";
import "./Post.css";

class Post extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      pageNumber: 1,
      pageSize: 2,
      isNextButtonDisabled: false,
    };
  }

  componentDidMount() {
    this.loadPosts();
  }

  loadPosts = () => {
    const { pageNumber, pageSize } = this.state;

    getAllPosts(pageSize, pageNumber)
      .then((response) => {
        this.setState({ posts: response.data });
        this.checkNextButtonState(pageNumber + 2);
      })
      .catch((error) => {
        console.error("Gönderiler alınamadı: ", error);
        this.checkNextButtonState(pageNumber + 2);
      });
  };

  loadNextPage = () => {
    const { pageNumber, pageSize } = this.state;

    const nextPageNumber = pageNumber + 1;

    getAllPosts(pageSize, nextPageNumber)
      .then((response) => {
        const newPosts = response.data;
        this.setState(
          (prevState) => ({
            posts: newPosts,
            pageNumber: nextPageNumber,
          }),
          () => {
            const twoPagesLater = nextPageNumber + 1;

            getAllPosts(pageSize, twoPagesLater)
              .then((response) => {
                const postsTwoPagesLater = response.data;
                this.checkNextButtonState(postsTwoPagesLater);
              })
              .catch((error) => {
                console.error("Gönderiler alınamadı: ", error);
                this.checkNextButtonState([]);
              });
          }
        );
      })
      .catch((error) => {
        console.error("Gönderiler alınamadı: ", error);
        this.checkNextButtonState([]);
      });
  };

  checkNextButtonState = (newPosts) => {
    this.setState({ isNextButtonDisabled: newPosts.length === 0 });
  };

  loadPreviousPage = () => {
    this.isNextButtonDisabled = false;
    this.setState(
      (prevState) => ({
        pageNumber: prevState.pageNumber - 1,
      }),
      () => {
        this.loadPosts();
        this.checkNextButtonState(this.state.pageNumber + 1);
      }
    );
  };

  render() {
    const { posts, pageNumber, isNextButtonDisabled } = this.state;

    return (
      <div className="blog-container">
        <h1>Blog Sayfası</h1>
        {posts.map((post) => (
          <div className="post" key={post.contentId}>
            <h2>{post.contentTitle}</h2>
            <p>{post.content}</p>
            <p>Yazar: {post.contentAuthorName}</p>
            <p>Kategori: {post.contentCategory}</p>
            <p>Yorum Sayısı: {post.numberOfCommentsOfTheContent}</p>
            <p>
              Yayın Tarihi:{" "}
              {new Date(post.contentReleaseDate).toLocaleString()}
            </p>
          </div>
        ))}

        <div className="pagination">
          <button
            onClick={this.loadPreviousPage}
            disabled={pageNumber === 1}
          >
            Önceki Sayfa
          </button>
          <button
            onClick={this.loadNextPage}
            disabled={isNextButtonDisabled}
          >
            Sonraki Sayfa
          </button>
        </div>
      </div>
    );
  }
}

export default Post;
