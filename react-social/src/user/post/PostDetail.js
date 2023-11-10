
import React, { Component } from "react";
import "./PostDetail.css"; 

class PostDetail extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: props.data,
      responseDate: props.responseDate,
      isSuccess: props.isSuccess,
    };
  }

  render() {
    const { data, responseDate, isSuccess } = this.state;

    return (
      <div className="container">
        <div className="post-detail">
          <h2 className="post-title">Post Detail</h2>
          {data && (
            <div>
              <h3 className="post-title">Post Information</h3>
              <div>
                <strong>Post ID:</strong> {data.postId}
              </div>
              <div>
                <strong>Author Name:</strong> {data.postAuthorName}
              </div>
              <div>
                <strong>Title:</strong> {data.postTitle}
              </div>
              <div>
                <strong>Content:</strong> {data.postContent}
              </div>
              <div>
                <strong>Release Date:</strong> {data.postReleaseDate}
              </div>
              <div>
                <strong>Category:</strong> {data.postCategory}
              </div>
              {data.postComments && data.postComments.length > 0 && (
                <div className="comments">
                  <h4 className="post-title">Comments</h4>
                  <ul>
                    {data.postComments.map((comment, index) => (
                      <li key={index} className="comment">
                        <div>
                          <strong>Author Name:</strong>{" "}
                          {comment.commentAuthorName}
                        </div>
                        <div>
                          <strong>Content:</strong> {comment.commentContent}
                        </div>
                        <div>
                          <strong>Date:</strong> {comment.commentDate}
                        </div>
                      </li>
                    ))}
                  </ul>
                </div>
              )}
            </div>
          )}
        </div>
      </div>
    );
  }
}

export default PostDetail;
