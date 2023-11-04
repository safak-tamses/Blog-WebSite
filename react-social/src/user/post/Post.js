import React, { Component } from 'react';
import { getAllPosts } from '../../util/APIUtils';
import './Post.css';

class Post extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      pageNumber: 1,
      pageSize: 2,
    };
  }

  componentDidMount() {
    this.loadPosts();
  }

  loadPosts = () => {
    const { pageNumber, pageSize } = this.state;

    getAllPosts(pageSize, pageNumber)
      .then(response => {
        this.setState({ posts: response.data });
      })
      .catch(error => {
        console.error('Gönderiler alınamadı: ', error);
      });
  };

  render() {
    const { posts, pageNumber } = this.state;

    return (
      <div className="blog-container">
        <h1>Blog Sayfası</h1>
        {posts.map(post => (
          <div className="post" key={post.contentId}>
            <h2>{post.contentTitle}</h2>
            <p>{post.content}</p>
            <p>Yazar: {post.contentAuthorName}</p>
            <p>Kategori: {post.contentCategory}</p>
            <p>Yorum Sayısı: {post.numberOfCommentsOfTheContent}</p>
            <p>Yayın Tarihi: {new Date(post.contentReleaseDate).toLocaleString()}</p>
          </div>
        ))}

        <div className="pagination">
          <button onClick={this.loadPreviousPage} disabled={pageNumber === 1}>Önceki Sayfa</button>
          <button onClick={this.loadNextPage}>Sonraki Sayfa</button>
        </div>
      </div>
    );
  }

  loadNextPage = () => {
    this.setState(prevState => ({
      pageNumber: prevState.pageNumber + 1
    }), this.loadPosts);
  };

  loadPreviousPage = () => {
    this.setState(prevState => ({
      pageNumber: prevState.pageNumber - 1
    }), this.loadPosts);
  };

  render() {
    const { posts, pageNumber } = this.state;

    return (
      <div className="blog-container">
        <h1>Blog Sayfası</h1>
        {posts.map(post => (
          <div className="post" key={post.contentId}>
            <h2>{post.contentTitle}</h2>
            <p>{post.content}</p>
            <p>Yazar: {post.contentAuthorName}</p>
            <p>Kategori: {post.contentCategory}</p>
            <p>Yorum Sayısı: {post.numberOfCommentsOfTheContent}</p>
            <p>Yayın Tarihi: {new Date(post.contentReleaseDate).toLocaleString()}</p>
          </div>
        ))}

        <div className="pagination">
          <button onClick={this.loadPreviousPage} disabled={pageNumber === 1}>Önceki Sayfa</button>
          <button onClick={this.loadNextPage}>Sonraki Sayfa</button>
        </div>
      </div>
    );
  }
}

export default Post;
