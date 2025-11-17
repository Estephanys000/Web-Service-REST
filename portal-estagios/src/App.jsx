import React, { useState, useEffect } from 'react';

// URL base da sua API Spring Boot (que está rodando em localhost:8080)
const BASE_URL = 'https://didactic-spork-46pqwq4vr7qc7g59-8080.app.github.dev';

const IconBriefcase = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="mr-2 h-5 w-5 text-blue-500">
    <rect width="20" height="14" x="2" y="7" rx="2" ry="2"></rect>
    <path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path>
  </svg>
);

const IconBuilding = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="mr-2 h-5 w-5 text-gray-400">
    <rect width="16" height="20" x="4" y="2" rx="2" ry="2"></rect>
    <path d="M9 22v-4h6v4"></path><path d="M8 6h.01"></path><path d="M16 6h.01"></path><path d="M12 6h.01"></path><path d="M12 10h.01"></path><path d="M12 14h.01"></path><path d="M16 10h.01"></path><path d="M16 14h.01"></path><path d="M8 10h.01"></path><path d="M8 14h.01"></path>
  </svg>
);

const IconUser = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="mr-2 h-5 w-5">
    <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
    <circle cx="12" cy="7" r="4"></circle>
  </svg>
);

const IconArrowLeft = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="mr-2 h-5 w-5">
    <path d="m12 19-7-7 7-7"></path><path d="M19 12H5"></path>
  </svg>
);

const IconCheckCircle = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="h-5 w-5 text-green-500">
    <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
    <path d="m9 11 3 3L22 4"></path>
  </svg>
);

const IconAlertTriangle = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="h-5 w-5 text-red-500">
    <path d="m21.73 18-8-14a2 2 0 0 0-3.46 0l-8 14A2 2 0 0 0 4 21h16a2 2 0 0 0 1.73-3Z"></path>
    <path d="M12 9v4"></path><path d="M12 17h.01"></path>
  </svg>
);

// --- Componentes Utilitários ---

/**
 * Um spinner de carregamento simples
 */
const LoadingSpinner = () => (
  <div className="flex justify-center items-center p-10">
    <div className="w-8 h-8 border-4 border-blue-500 border-t-transparent rounded-full animate-spin"></div>
  </div>
);

/**
 * Um componente para mostrar mensagens de sucesso ou erro
 */
const Notification = ({ message, type, onClose }) => {
  if (!message) return null;

  const isSuccess = type === 'success';
  const bgColor = isSuccess ? 'bg-green-100' : 'bg-red-100';
  const borderColor = isSuccess ? 'border-green-400' : 'border-red-400';
  const textColor = isSuccess ? 'text-green-700' : 'text-red-700';

  return (
    <div className={`fixed top-5 right-5 z-50 max-w-sm rounded-lg border ${borderColor} ${bgColor} p-4 shadow-lg`} role="alert">
      <div className="flex items-start">
        {isSuccess ? <IconCheckCircle /> : <IconAlertTriangle />}
        <div className="ml-3">
          <p className={`text-sm font-medium ${textColor}`}>{message}</p>
        </div>
        <button type="button" className="ml-auto -mx-1.5 -my-1.5" onClick={onClose}>
          <svg className={`h-5 w-5 ${textColor}`} fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <path fillRule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clipRule="evenodd"></path>
          </svg>
        </button>
      </div>
    </div>
  );
};

// --- Componentes de Página ---

/**
 * Página Principal: Lista todas as vagas de estágio ativas
 */
const HomePage = ({ onNavigate, onApply, setNotification }) => {
  const [vagas, setVagas] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchVagas = async () => {
      try {
        setLoading(true);
        const response = await fetch(`${BASE_URL}/vagas`);
        if (!response.ok) {
          throw new Error('Não foi possível carregar as vagas.');
        }
        const data = await response.json();
        // Filtra para mostrar apenas vagas ativas
        setVagas(data.filter(vaga => vaga.ativo));
      } catch (error) {
        setNotification(`Erro: ${error.message}`, 'error');
      } finally {
        setLoading(false);
      }
    };

    fetchVagas();
  }, [setNotification]);

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-gray-800">Portal de Vagas de Estágio</h1>
        <button
          onClick={() => onNavigate('COMPANY_FORM')}
          className="bg-blue-600 text-white py-2 px-4 rounded-lg font-medium hover:bg-blue-700 transition duration-300"
        >
          Empresa? Cadastre uma Vaga
        </button>
      </div>

      {loading ? (
        <LoadingSpinner />
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {vagas.length === 0 && (
            <p className="text-gray-600 col-span-full text-center">Nenhuma vaga ativa encontrada no momento.</p>
          )}
          {vagas.map(vaga => (
            <div key={vaga.id} className="bg-white rounded-lg shadow-md p-6 border border-gray-200 hover:shadow-lg transition-shadow duration-300">
              <h2 className="text-xl font-semibold text-gray-900 mb-2">{vaga.titulo}</h2>
              <div className="flex items-center text-gray-600 mb-4">
                <IconBuilding />
                <span>{vaga.empresa ? vaga.empresa.nomeFantasia : 'Empresa não informada'}</span>
              </div>
              <p className="text-gray-700 mb-4 h-24 overflow-y-auto">{vaga.descricao}</p>
              <button
                onClick={() => onApply(vaga.id)}
                className="w-full bg-green-500 text-white py-2 px-4 rounded-lg font-medium hover:bg-green-600 transition duration-300"
              >
                Inscrever-se
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

/**
 * Página de Formulário: Para empresas cadastrarem novas vagas
 */
const CompanyJobForm = ({ onNavigate, setNotification }) => {
  const [empresas, setEmpresas] = useState([]);
  const [loading, setLoading] = useState(true);
  
  // State do formulário
  const [titulo, setTitulo] = useState('');
  const [descricao, setDescricao] = useState('');
  const [dataPublicacao, setDataPublicacao] = useState(new Date().toISOString().split('T')[0]);
  const [selectedEmpresaId, setSelectedEmpresaId] = useState('');

  // Carrega a lista de empresas para o <select>
  useEffect(() => {
    const fetchEmpresas = async () => {
      try {
        setLoading(true);
        const response = await fetch(`${BASE_URL}/empresas`);
        if (!response.ok) throw new Error('Não foi possível carregar as empresas.');
        setEmpresas(await response.json());
      } catch (error) {
        setNotification(`Erro: ${error.message}`, 'error');
      } finally {
        setLoading(false);
      }
    };
    fetchEmpresas();
  }, [setNotification]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedEmpresaId) {
      setNotification('Por favor, selecione uma empresa.', 'error');
      return;
    }

    const vagaPayload = {
      titulo,
      descricao,
      dataPublicacao,
      ativo: true, // Novas vagas são ativas por padrão
      empresa: {
        id: parseInt(selectedEmpresaId, 10)
      }
    };

    try {
      const response = await fetch(`${BASE_URL}/vagas`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(vagaPayload)
      });

      if (!response.ok) {
        throw new Error('Falha ao cadastrar a vaga.');
      }

      setNotification('Vaga cadastrada com sucesso!', 'success');
      onNavigate('HOME'); // Volta para a home
    } catch (error) {
      setNotification(`Erro: ${error.message}`, 'error');
    }
  };

  return (
    <div className="max-w-2xl mx-auto bg-white p-8 rounded-lg shadow-md border border-gray-200">
      <button
        onClick={() => onNavigate('HOME')}
        className="flex items-center text-blue-600 hover:underline mb-4"
      >
        <IconArrowLeft />
        Voltar para o portal
      </button>
      <h2 className="text-2xl font-bold text-gray-800 mb-6">Cadastrar Nova Vaga</h2>
      
      {loading ? <LoadingSpinner /> : (
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="empresa" className="block text-sm font-medium text-gray-700 mb-1">Empresa*</label>
            <select
              id="empresa"
              value={selectedEmpresaId}
              onChange={(e) => setSelectedEmpresaId(e.target.value)}
              required
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="" disabled>Selecione a empresa</option>
              {empresas.map(emp => (
                <option key={emp.id} value={emp.id}>{emp.nomeFantasia}</option>
              ))}
            </select>
          </div>

          <div className="mb-4">
            <label htmlFor="titulo" className="block text-sm font-medium text-gray-700 mb-1">Título da Vaga*</label>
            <input
              type="text"
              id="titulo"
              value={titulo}
              onChange={(e) => setTitulo(e.target.value)}
              required
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div className="mb-4">
            <label htmlFor="descricao" className="block text-sm font-medium text-gray-700 mb-1">Descrição da Vaga*</label>
            <textarea
              id="descricao"
              rows="4"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
              required
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            ></textarea>
          </div>

          <div className="mb-6">
            <label htmlFor="dataPublicacao" className="block text-sm font-medium text-gray-700 mb-1">Data de Publicação*</label>
            <input
              type="date"
              id="dataPublicacao"
              value={dataPublicacao}
              onChange={(e) => setDataPublicacao(e.target.value)}
              required
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 px-4 rounded-lg font-medium hover:bg-blue-700 transition duration-300"
          >
            Publicar Vaga
          </button>
        </form>
      )}
    </div>
  );
};

/**
 * Página de Formulário: Para alunos se inscreverem em uma vaga
 */
const StudentApplyForm = ({ onNavigate, vagaId, setNotification }) => {
  const [estudantes, setEstudantes] = useState([]);
  const [vaga, setVaga] = useState(null);
  const [loading, setLoading] = useState(true);

  // State do formulário
  const [selectedEstudanteId, setSelectedEstudanteId] = useState('');
  const [mensagemApresentacao, setMensagemApresentacao] = useState('');

  // Carrega dados da vaga e lista de estudantes
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        // 1. Busca a vaga específica
        const vagaResponse = await fetch(`${BASE_URL}/vagas/${vagaId}`);
        if (!vagaResponse.ok) throw new Error('Não foi possível carregar a vaga.');
        setVaga(await vagaResponse.json());

        // 2. Busca todos os estudantes
        const estudantesResponse = await fetch(`${BASE_URL}/estudantes`);
        if (!estudantesResponse.ok) throw new Error('Não foi possível carregar os estudantes.');
        setEstudantes(await estudantesResponse.json());

      } catch (error) {
        setNotification(`Erro: ${error.message}`, 'error');
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [vagaId, setNotification]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedEstudanteId) {
      setNotification('Por favor, selecione seu nome.', 'error');
      return;
    }

    const inscricaoPayload = {
      dataInscricao: new Date().toISOString().split('T')[0], // Data de hoje
      status: "Pendente",
      mensagemApresentacao,
      estudante: {
        id: parseInt(selectedEstudanteId, 10)
      },
      vaga: {
        id: vagaId
      }
    };

    try {
      const response = await fetch(`${BASE_URL}/inscricoes`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(inscricaoPayload)
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Falha ao realizar inscrição.');
      }

      setNotification('Inscrição realizada com sucesso!', 'success');
      onNavigate('HOME'); // Volta para a home
    } catch (error) {
      setNotification(`Erro: ${error.message}`, 'error');
    }
  };

  if (loading) return (
    <div className="max-w-2xl mx-auto"><LoadingSpinner /></div>
  );
  
  if (!vaga) return (
     <div className="max-w-2xl mx-auto bg-white p-8 rounded-lg shadow-md border border-gray-200">
       <h2 className="text-2xl font-bold text-red-600 mb-6">Vaga não encontrada</h2>
        <button
          onClick={() => onNavigate('HOME')}
          className="flex items-center text-blue-600 hover:underline mb-4"
        >
          <IconArrowLeft />
          Voltar para o portal
        </button>
     </div>
  );

  // Lógica para vaga inativa
  if (!vaga.ativo) {
     return (
       <div className="max-w-2xl mx-auto bg-white p-8 rounded-lg shadow-md border border-gray-200">
        <h2 className="text-2xl font-bold text-gray-800 mb-2">Inscrição para: {vaga.titulo}</h2>
        <p className="text-lg text-red-600 font-medium mb-6">Esta vaga não está mais aceitando inscrições.</p>
        <button
          onClick={() => onNavigate('HOME')}
          className="flex items-center text-blue-600 hover:underline mb-4"
        >
          <IconArrowLeft />
          Voltar para o portal
        </button>
      </div>
     )
  }

  return (
    <div className="max-w-2xl mx-auto bg-white p-8 rounded-lg shadow-md border border-gray-200">
      <button
        onClick={() => onNavigate('HOME')}
        className="flex items-center text-blue-600 hover:underline mb-4"
      >
        <IconArrowLeft />
        Voltar para o portal
      </button>

      <div className="mb-6">
        <h2 className="text-2xl font-bold text-gray-800">Inscrição para: {vaga.titulo}</h2>
        <p className="text-lg text-gray-600">{vaga.empresa.nomeFantasia}</p>
      </div>
      
      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label htmlFor="estudante" className="block text-sm font-medium text-gray-700 mb-1">Seu Nome*</label>
          <select
            id="estudante"
            value={selectedEstudanteId}
            onChange={(e) => setSelectedEstudanteId(e.target.value)}
            required
            className="w-full p-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
          >
            <option value="" disabled>Selecione seu nome na lista</option>
            {estudantes.map(est => (
              <option key={est.id} value={est.id}>{est.nome} (Ano: {est.anoIngresso})</option>
            ))}
          </select>
        </div>

        <div className="mb-6">
          <label htmlFor="mensagem" className="block text-sm font-medium text-gray-700 mb-1">Mensagem de Apresentação</label>
          <textarea
            id="mensagem"
            rows="4"
            value={mensagemApresentacao}
            onChange={(e) => setMensagemApresentacao(e.target.value)}
            placeholder="Fale um pouco sobre você e por que tem interesse na vaga..."
            className="w-full p-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
          ></textarea>
        </div>

        <button
          type="submit"
          className="w-full bg-green-500 text-white py-2 px-4 rounded-lg font-medium hover:bg-green-600 transition duration-300"
        >
          Confirmar Inscrição
        </button>
      </form>
    </div>
  );
};


/**
 * Componente Principal - Gerencia o estado e a navegação
 */
function App() {
  // Estado para gerenciar qual "página" está sendo exibida
  const [currentPage, setCurrentPage] = useState('HOME'); // HOME, COMPANY_FORM, STUDENT_APPLY_FORM
  
  // Estado para saber para qual vaga o aluno está se inscrevendo
  const [selectedVagaId, setSelectedVagaId] = useState(null);
  
  // Estado para mensagens de notificação
  const [notification, setNotification] = useState({ message: '', type: '' });

  // Função para fechar a notificação
  const closeNotification = () => setNotification({ message: '', type: '' });

  // Função para exibir uma notificação
  const showNotification = (message, type = 'success') => {
    setNotification({ message, type });
    // Limpa a notificação após 4 segundos
    setTimeout(() => closeNotification(), 4000);
  };

  // --- Funções de Navegação ---

  const navigateTo = (page) => {
    setCurrentPage(page);
  };

  const handleApplyClick = (vagaId) => {
    setSelectedVagaId(vagaId);
    setCurrentPage('STUDENT_APPLY_FORM');
  };

  // Renderiza a página correta com base no estado 'currentPage'
  const renderPage = () => {
    switch (currentPage) {
      case 'COMPANY_FORM':
        return <CompanyJobForm onNavigate={navigateTo} setNotification={showNotification} />;
      case 'STUDENT_APPLY_FORM':
        return <StudentApplyForm onNavigate={navigateTo} vagaId={selectedVagaId} setNotification={showNotification} />;
      case 'HOME':
      default:
        return <HomePage onNavigate={navigateTo} onApply={handleApplyClick} setNotification={showNotification} />;
    }
  };

  return (
    // Usamos Tailwind CSS para estilização (assumindo que está configurado no seu projeto)
    <div className="bg-gray-100 min-h-screen font-inter">
      {/* Notificação flutuante */}
      <Notification 
        message={notification.message} 
        type={notification.type} 
        onClose={closeNotification} 
      />

      {/* Cabeçalho Fixo */}
      <header className="bg-white shadow-sm border-b border-gray-200">
        <nav className="container mx-auto px-4 md:px-8 py-4 flex items-center">
          <IconBriefcase />
          <h1 className="text-xl font-bold text-gray-800">Mackenzie Estágios</h1>
        </nav>
      </header>

      {/* Conteúdo Principal da Página */}
      <main className="container mx-auto p-4 md:p-8">
        {renderPage()}
      </main>
    </div>
  );
}

export default App;