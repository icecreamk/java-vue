import { useEffect } from "react";
import axios from "axios";
export default function Home() {
  useEffect(() => {
    getEbook();
  }, []);

  const getEbook = async () => {
    axios
      .get("http://localhost:8881/ebook/list", {
        params: {
          name: "vue",
        },
      })
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div>
      <h1>Home</h1>
    </div>
  );
}
