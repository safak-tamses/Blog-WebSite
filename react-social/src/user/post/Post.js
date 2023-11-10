import React, { Component } from "react";
import { getAllPosts, getPostDetail } from "../../util/APIUtils";
import "./Post.css";
import PostDetail from "./PostDetail";

class Post extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      pageNumber: 1,
      pageSize: 2,
      isNextButtonDisabled: false,
      selectedPostId: null,
      postDetail: null,
      isModalOpen: false,
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

  showPortDetail = (postId) => {
    getPostDetail(postId)
      .then((response) => {
        this.setState({
          selectedPostId: postId,
          postDetail: response.data,
          isModalOpen: true,
        });
      })
      .catch((error) => {
        console.error("Gönderiler alınamadı: ", error);
      });
  };

  clearPostDetail = () => {
    this.setState({
      selectedPostId: null,
      postDetail: null,
      isModalOpen: false,
    });
  };

  render() {
    const {
      posts,
      pageNumber,
      isNextButtonDisabled,
      selectedPostId,
      postDetail,
      isModalOpen,
    } = this.state;

    return (
      <div className="blog-container">
        <h1>Blog Sayfası</h1>
        {!isModalOpen && (
          <div>
            {posts.map((post) => (
              <button
                className="post"
                key={post.contentId}
                onClick={() => {
                  this.showPortDetail(post.contentId);
                }}
              >
                <h2>{post.contentTitle}</h2>
                <p>{post.content}</p>
                <p>Yazar: {post.contentAuthorName}</p>
                <p>Kategori: {post.contentCategory}</p>
                <p>Yorum Sayısı: {post.numberOfCommentsOfTheContent}</p>
                <p>
                  Yayın Tarihi:{" "}
                  {new Date(post.contentReleaseDate).toLocaleString()}
                </p>
              </button>
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
        )}

        {isModalOpen && (
          <div className="modal" onClick={this.clearPostDetail}>
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
              <div className="butonCloseAll">
                <button onClick={this.clearPostDetail} className="closeButton">
                  Close
                </button>
              </div>
              <PostDetail
                data={postDetail}
                responseDate={new Date().toLocaleString()}
                isSuccess={true}
              />
            </div>
          </div>
        )}
      </div>
    );
  }
}

export default Post;
