import Entry from "./Entry";
import EntryContainer from "./EntryContainer";

const EntrySingle = ({ data }) => {
  // temp data
  if (data == null) {
    data = {
      content: "lorem ipsum",
      username: "koraykzly",
      datetime: "14.10.2023 20:24",
      favCount: 5,
      topicTitle: "topic",
      topicId: 1
    };
  }

  return (
    <div className="topic">
      <h1>
        <a>{data.topicTitle}</a>
      </h1>
      <Entry data={data}></Entry>
    </div>
  );
};

export default EntrySingle;
